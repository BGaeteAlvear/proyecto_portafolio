package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Category;
import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.service.ICategoryService;
import com.feriavirtual.app.models.service.IProductService;
import com.feriavirtual.app.models.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


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
@RequestMapping("/purchase-order")
@SessionAttributes("pruchase-order")
public class PurchaseOrderController {



    private final IProductService productService;
    private final ICategoryService categoryService;
    private final Logger log= LoggerFactory.getLogger(getClass());

    @Autowired
    private IUploadFileService uploadFileService;

    public PurchaseOrderController(IProductService productService, ICategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
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
    public String create(Map<String,Object> model, RedirectAttributes flash){
        Product product= new Product();
        List<Category> listCategories = categoryService.getAll();

        System.out.println("cantidad de categorias "+listCategories.size());
        if(listCategories.size()>0){
            model.put("title_header", "CREAR PRODUCTO");
            model.put("title","Crear Producto");
            model.put("product", product);
            model.put("list_categories", listCategories);
            return "/product/form";
        }else{
            model.put("title_header", "CATEGORIAS");
            model.put("title","CATEGORIAS");
            model.put("product", product);
            model.put("subtitle_header","Mantenedor de  Categorias");
            model.put("title_page", "PLATAFORMA MAIPO GRANDE | CATEGORIAS");
            model.put("list_categories", listCategories);
            flash.addFlashAttribute("info","POR FAVOR CREA UNA CATEGORIA ANTES DE CREAR UN PRODUCTO");
            return "redirect:/category/index";
        }
    }


    /*@GetMapping (value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verImagen(@PathVariable String filename){

        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ recurso.getFilename()+ "\"")
                .body(recurso);
    }*/


    @PostMapping("/form")
   public String  store(@Valid Product product, BindingResult result, Model model){

        return "redirect:/purchase-order/index";
    }

    //@RequestMapping(value="/eliminar/{id}")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id")Long id, RedirectAttributes flash){
        if (id>0){
            Product product = productService.findById(id);

            productService.delete(id);

                if(uploadFileService.delete(product.getImage())){
                    flash.addFlashAttribute("warning", "Imagen: "+ product.getImage()+" eliminada con éxito");
                }

        }
        return "redirect:/product/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Product product = null;
        List<Category> listCategories = categoryService.getAll();
        if(id > 0){
            product = productService.findById(id);

           if (product == null){
                flash.addFlashAttribute("error", "El id del producto no existe en la base de datos");
                return "redirect:/product/index";
            }
        }else {
            flash.addFlashAttribute("error", "El ID del producto no puede ser cero!");
            return "redirect:/product/index";
        }
        model.put("product", product);
        model.put("list_categories", listCategories);
        model.put("title_header", "Editar Producto");
        model.put("title", "Editar producto");
        return "/product/form";
    }



}
