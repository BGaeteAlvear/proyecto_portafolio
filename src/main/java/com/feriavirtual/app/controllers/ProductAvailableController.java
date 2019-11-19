package com.feriavirtual.app.controllers;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.entity.ProductAvailable;
import com.feriavirtual.app.models.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product-available")
@SessionAttributes("productAvailable")

public class ProductAvailableController {


    @Autowired
    private IPersonService personService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductAvailableService productAvailableService;

    @GetMapping("/index")
    public String index (Model model, HttpSession session){
        Person productor =  (Person) session.getAttribute("userSession");
        List<ProductAvailable> listProductsAvailable = productAvailableService.findByPerson(productor);
        System.out.println("cantidad de productos : "+productAvailableService.findByPerson(productor).size());
        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "PRODUCTOS DISPONIBLES");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | PRODUCTOS DISPONIBLES");
        model.addAttribute("subtitle_header", "Mantenedor de Productos Disponibles");
        model.addAttribute("list_products_available", listProductsAvailable);
        return "/product-available/index";
    }


    @GetMapping("/form")
    public String create(Map<String,Object> model){
        ProductAvailable productAvailable= new ProductAvailable();
        List<Product> ListProduct = productService.getAll();
        model.put("title_header", "Crear Producto Disponible");
        model.put("title","Crear Producto Disponible");
        model.put("productAvailable", productAvailable);
        model.put("ListProduct", ListProduct);
        return "/product-available/form";
    }

    @PostMapping("/form")
    public String  store(@Valid ProductAvailable productAvailable, BindingResult result, Model model,
                         RedirectAttributes flash, SessionStatus status , HttpSession session){
        ProductAvailable pa;
        Person productor =  (Person) session.getAttribute("userSession");
        List<Product> ListProduct = productService.getAll();
        if (result.hasErrors()){
            model.addAttribute("title_header", "PRODUCTOS DISPONIBLES");
            model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | PRODUCTOS DISPONIBLES");
            model.addAttribute("subtitle_header", "Mantenedor de Productos Disponibles");
            model.addAttribute("title","Crear Producto Disponible");
            model.addAttribute("ListProduct", ListProduct);
            return "/product-available/form";
        }

        if (productAvailable.getId() !=null){
            pa = productAvailableService.findById(productAvailable.getId());
        }else {
            pa  =  new ProductAvailable();
        }

        if (productAvailable.getPrice()>0){
            pa.setPrice(productAvailable.getPrice());
        }else{
            flash.addFlashAttribute("error", "El precio debe ser mayor a 0");
            return "redirect:/product-available/form";
        }

        if (productAvailable.getStock()>0){
            pa.setStock(productAvailable.getStock());
        }else{
            flash.addFlashAttribute("error", "El stock debe ser mayor a 0");
            return "redirect:/product-available/form";
        }

        if (productAvailable.getProduct() !=null){
            pa.setProduct(productAvailable.getProduct());
        }else{
            flash.addFlashAttribute("error", "El producto es requerido!");
            return "redirect:/product-available/form";
        }

        if (productAvailable.getCreatedAt()!=null){
            pa.setCreatedAt(productAvailable.getCreatedAt());
        }

        if (productAvailable.getUpdatedAt()!=null){
            pa.setUpdatedAt(productAvailable.getUpdatedAt());
        }

        if (productAvailable.getDate_expire()!=null){
            pa.setDate_expire(productAvailable.getDate_expire());
        }else{
            flash.addFlashAttribute("error", "La fecha de expiración del producto es requerido!");
            return "redirect:/product-available/form";
        }

        pa.setPerson(productor);
        pa.setStatus(productAvailable.getStatus());
        pa.setStock_unity(productAvailable.getStock_unity());

        if ( pa.getProduct() != null){
            productAvailableService.save(pa);
            status.setComplete();
            flash.addFlashAttribute("success", "Producto Agregado con exito!");
            return "redirect:/product-available/index";
        }else{
            flash.addFlashAttribute("error", "No cargo el producto!");
            return "redirect:/product-available/form";
        }



    }

    //@RequestMapping(value="/eliminar/{id}")
    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable(value = "id")Long id, RedirectAttributes flash){
        if (id>0){
            ProductAvailable productAvailable = productAvailableService.findById(id);

            productAvailableService.delete(id);
        }
        flash.addFlashAttribute("warning", "El producto ha sido eliminado");
        return "redirect:/product-available/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        ProductAvailable productAvailable = null;
        List<Product> product = productService.getAll();
        if(id > 0){
            productAvailable = productAvailableService.findById(id);

            if (productAvailable == null){
                flash.addFlashAttribute("error", "El id del producto disponible no existe en la base de datos");
                return "redirect:/product-available/index";
            }
        }else {
            flash.addFlashAttribute("error", "El id del producto disponible no puede ser cero");
            return "redirect:/product-available/index";
        }
        model.put("productAvailable", productAvailable);
        model.put("listProduct", product);
        model.put("title_header", "Editar Producto Disponible" );
        model.put("title", "Editar producto disponible");
        return "/product-available/form";
    }

}
