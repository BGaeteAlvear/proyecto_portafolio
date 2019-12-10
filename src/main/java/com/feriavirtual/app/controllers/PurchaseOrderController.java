package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Category;
import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.entity.PurchaseOrder;
import com.feriavirtual.app.models.service.ICategoryService;
import com.feriavirtual.app.models.service.IProductService;
import com.feriavirtual.app.models.service.IPurchaseOrderService;
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
@SessionAttributes("purchase-order")
public class PurchaseOrderController {



    private final IProductService productService;
    private final IPurchaseOrderService purchaseOrderService;
    private final ICategoryService categoryService;
    private final IPurchaseOrderService purchaseOrderService;
    private final Logger log= LoggerFactory.getLogger(getClass());


    public PurchaseOrderController(IProductService productService, ICategoryService categoryService, IPurchaseOrderService purchaseOrderService) {
        this.productService = productService;
        this.purchaseOrderService = purchaseOrderService;
        this.categoryService = categoryService;
        this.purchaseOrderService = purchaseOrderService;
    }


    @GetMapping("/detail")
    public String index (Model model){
        List<Product> listProducts = productService.getAll();
        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "ORDER DE COMPRA");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | ORDEN DE COMPRA");
        model.addAttribute("subtitle_header", "DETALLE DE ORDEN DE COMPRA");

        return "/purchase-order/detail";
    }


    @PostMapping("/form")
   public String  store(@Valid PurchaseOrder purchaseOrder, Model model){
        purchaseOrderService.save(purchaseOrder);
        List<PurchaseOrder> listOrders = purchaseOrderService.getAll();
        model.addAttribute("listOrders", listOrders);
        return "redirect:/purchase-order/index";
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

    // este es el controlador brian
    @GetMapping("/purchaseOrderByUserId/{id}")
    public String purchaseOrderByUserId(@PathVariable(value = "id")Long id, Model model){

        List<PurchaseOrder> list =  purchaseOrderService.getPurchaseOrderByPersonId(id);

        model.addAttribute("lista_ordenes" , list);

        return "/purchase-order/index";
    }



}
