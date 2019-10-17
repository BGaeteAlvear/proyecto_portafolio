package com.feriavirtual.app.controllers;


import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*

   EJEMPLO ESTRUCTURA CONTROLLER
   =========================================================================
   Verb	  | URI	                   | Action	        | Route Name
   =========================================================================
   GET	      | /photos	               | index	        | photos.index
   GET	      | /photos/create	       | create	        | photos.create
   POST	    | /photos	               | store	        | photos.store
   GET	      | /photos/{photo}	       | show	        | photos.show
   GET	      | /photos/{photo}/edit   | edit	        | photos.edit
   PUT/PATCH |	/photos/{photo}	       | update         | photos.update
   DELETE	  | /photos/{photo}	       | destroy	    | photos.destroy
   =========================================================================
    */
@Controller
@RequestMapping("/product")
@SessionAttributes("product")
public class ProductController {

    private final IProductService productService;

    private final Logger log= LoggerFactory.getLogger(getClass());


    public ProductController(IProductService productService) { this.productService = productService;
    }

    @GetMapping("/index")
    public String index (Model model){
        List<Product> listProducts = productService.getAll();
        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "PRODUCTOS");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | PRODUCTOS");
        model.addAttribute("subtitle_header", "Mantenedor de Productos");
        model.addAttribute("list_products", listProducts);
        return "/product/index";
    }



    @GetMapping("/form")
    public String create(Map<String,Object> model){
        Product product= new Product();
        model.put("title_header", "Crear Producto");
        model.put("title","Crear Producto");
        model.put("product", product);
        return "/product/form";
    }

    @PostMapping("/form")
    public String  store(@Valid Product product, BindingResult result, Model model,
                         @RequestParam("image") MultipartFile image, RedirectAttributes flash, SessionStatus status){

        if(!image.isEmpty()){
            String uniqueFilename = UUID.randomUUID().toString()+"_"+image.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(image.getOriginalFilename());
            Path rootAbsolutePath =rootPath.toAbsolutePath();
            log.info("rootPath: "+ rootPath);
            log.info("rootAbsolutePath: "+ rootAbsolutePath);

            try {
               Files.copy(image.getInputStream(), rootAbsolutePath);
               flash.addFlashAttribute("Se ha cargado correctamente '"+ image.getOriginalFilename()+ "'");
                product.setImage(image.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result.hasErrors()){
                model.addAttribute("title","Crear Producto");
                return "form";
            }

            if (product !=null){
                productService.save(product);
                status.setComplete();
            }


        }
        return "redirect:/product/index";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable(value = "id")Long id){
        if (id>0){
            productService.delete(id);
        }
        return "redirect:/product/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Product product = null;
        if(id > 0){
            product = productService.findById(id);
            if (product == null){
                flash.addFlashAttribute("error", "El ID del producto no existe en la BBDD!");
                return "redirect:/product/index";
            }
        }else {
            flash.addFlashAttribute("error", "El ID del producto no puede ser cero!");
            return "redirect:/product/index";
        }
        model.put("product", product);
        model.put("title_header", "Editar Producto");
        model.put("title", "Editar producto");
        return "/product/form";
    }



}
