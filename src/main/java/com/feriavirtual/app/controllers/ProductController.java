package com.feriavirtual.app.controllers;


import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
@SessionAttributes("product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) { this.productService = productService;
    }
    @GetMapping("/listar")
    public String listar (Model model){
        List<Product> lista = productService.getAll();
        model.addAttribute("titulo","Lista de Productos");
        model.addAttribute("lista",lista);
        return "/product/listar";

    }

    @GetMapping("/form")
    public String crear(Map<String,Object> model){
        Product product= new Product();
        model.put("titulo","Crear Producto");
        model.put("product", product);
        return "/product/form";
    }

    @PostMapping("/form")
    public String  guardar (@Valid Product product, BindingResult result, Model model, SessionStatus status){
        if (product !=null){
            productService.save(product);
            status.setComplete();
        }
        return "redirect:/product/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id")Long id){
        if (id>0){
            productService.delete(id);
        }
        return "redirect:/product/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Product product = null;
        if(id > 0){
            product = productService.findById(id);
            if (product == null){
                flash.addFlashAttribute("error", "El ID del producto no existe en la BBDD!");
                return "redirect:/product/listar";
            }
        }else {
            flash.addFlashAttribute("error", "El ID del producto no puede ser cero!");
            return "redirect:/product/listar";
        }
        model.put("product", product);
        model.put("titulo", "Editar producto");
        return "/product/form";
    }



}
