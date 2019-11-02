package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Category;
import com.feriavirtual.app.models.entity.ProductAvailable;
import com.feriavirtual.app.models.service.ICategoryService;
import com.feriavirtual.app.models.service.IProductAvailableService;
import com.feriavirtual.app.models.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productAvailable")
@SessionAttributes("productAvailable")

public class ProductAvailableController {
    private final IProductAvailableService productAvailableService;
    private final ICategoryService categoryService;

    @Autowired
    private IUploadFileService uploadFileService;

    public ProductAvailableController(IProductAvailableService productAvailableService, ICategoryService categoryService) {
        this.productAvailableService = productAvailableService;
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public String index (Model model){
        List<ProductAvailable> listProductsAvailable = productAvailableService.getAll();
        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "PRODUCTOS DISPONIBLES");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | PRODUCTOS DISPONIBLES");
        model.addAttribute("subtitle_header", "Mantenedor de Productos Disponibles");
        model.addAttribute("list_products_available", listProductsAvailable);
        return "/productAvailable/index";
    }


    @GetMapping("/form")
    public String create(Map<String,Object> model){
        ProductAvailable productAvailable= new ProductAvailable();
        List<Category> listCategories = categoryService.getAll();
        model.put("title_header", "Crear Producto Disponible");
        model.put("title","Crear Producto Disponible");
        model.put("productAvailable", productAvailable);
        model.put("list_categories", listCategories);
        return "/productAvailable/form";
    }


    @GetMapping (value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verImagen(@PathVariable String filename){

        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ recurso.getFilename()+ "\"")
                .body(recurso);
    }



    @PostMapping("/form")
    public String  store(@Valid ProductAvailable productAvailable, BindingResult result, Model model,
                         @RequestParam("file") MultipartFile image, RedirectAttributes flash, SessionStatus status){

        if(!image.isEmpty()){
            if (productAvailable.getId()!=null && productAvailable.getId()>0
                    && productAvailable.getImage()!=null
                    && productAvailable.getImage().length()>0  ){

                uploadFileService.delete(productAvailable.getImage());
            }
            String uniqueFilename= null;
            try {
                uniqueFilename = uploadFileService.copy(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

            flash.addFlashAttribute("info","Se ha cargado correctamente '"+ uniqueFilename+ "'");
            productAvailable.setImage(uniqueFilename);

            if (result.hasErrors()){
                model.addAttribute("title","Crear Producto Disponible");
                return "/productAvailable/form";
            }

            if (productAvailable !=null){
                productAvailableService.save(productAvailable);
                status.setComplete();
            }


        }
        return "redirect:/productAvailable/index";
    }

    //@RequestMapping(value="/eliminar/{id}")
    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable(value = "id")Long id, RedirectAttributes flash){
        if (id>0){
            ProductAvailable productAvailable = productAvailableService.findById(id);

            productAvailableService.delete(id);

            if(uploadFileService.delete(productAvailable.getImage())){
                flash.addFlashAttribute("info", "Imagen: "+ productAvailable.getImage()+" eliminada con éxito");
            }

        }
        return "redirect:/productAvailable/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        ProductAvailable productAvailable = null;
        List<Category> listCategories = categoryService.getAll();
        if(id > 0){
            productAvailable = productAvailableService.findById(id);

            if (productAvailable == null){
                flash.addFlashAttribute("error", "El ID del producto no existe en la BBDD!");
                return "redirect:/productAvailable/index";
            }
        }else {
            flash.addFlashAttribute("error", "El ID del producto no puede ser cero!");
            return "redirect:/productAvailable/index";
        }
        model.put("productAvailable", productAvailable);
        model.put("list_categories", listCategories);
        model.put("title_header", "Editar Producto Disponible" );
        model.put("title", "Editar producto disponible");
        return "/productAvailable/form";
    }

}
