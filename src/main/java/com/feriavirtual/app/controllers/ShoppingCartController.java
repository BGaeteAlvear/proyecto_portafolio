package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.*;
import com.feriavirtual.app.models.repository.IPurchaseOrderRepository;
import com.feriavirtual.app.models.service.*;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping("/shopping-cart")
@SessionAttributes("shopping-cart")
public class ShoppingCartController {

    @Autowired
    private IProductAvailableService productAvailableService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IPurchaseOrderService purchaseOrderService;


    @GetMapping("/index")
    public String index (Model model, HttpSession session){
        List<Category> listCategory = categoryService.getAll();
        Person productor =  (Person) session.getAttribute("userSession");
        System.out.println("cantidad de productos : "+productAvailableService.findByPerson(productor).size());
        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "TIENDA DE PRODUCTOS");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | CATEGORÍAS DISPONIBLES");
        model.addAttribute("subtitle_header", "Tienda online Maipo Grande S.A");
        model.addAttribute("listCategory", listCategory);
        return "/shopping-cart/index";
    }


    @GetMapping("/product/{id}")
    public String productList (@PathVariable(value = "id")Long id,Model model, HttpSession session){
        Category category = categoryService.findById(id);
        List<Product> listProduct = productService.findByCategory(category);
        model.addAttribute("title_header", "TIENDA DE PRODUCTOS");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | "+category.getName().toUpperCase());
        model.addAttribute("subtitle_header", "PRODUCTOS / "+category.getName().toUpperCase());
        model.addAttribute("listProduct", listProduct);
        return "/shopping-cart/product";
    }

    @GetMapping("/product/detail/{id}")
    public String productDetail (@PathVariable(value = "id")Long id,Model model, HttpSession session){

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        Product product = productService.findById(id);

        System.out.println(product.getName());

        Person customer =  (Person) session.getAttribute("userSession");
        String type = "externo";

        Person p2 = personService.getById(customer.getId());

        System.out.println(p2.getName());

        purchaseOrder.setCustomer_type(type);
        purchaseOrder.setProduct_id(product.getId());
        purchaseOrder.setProduct(product);
        purchaseOrder.setUnity_order(1);
        purchaseOrder.setPerson(customer);
        purchaseOrder.setCustomer_id(customer.getId());

        System.out.println("ORDER PERSON : "+ purchaseOrder.getPerson().getName());

        model.addAttribute("title_header", "TIENDA DE PRODUCTOS");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | "+product.getName().toUpperCase());
        model.addAttribute("subtitle_header","PRODUCTOS  / "+product.category.getName().toUpperCase()+" / "+product.getName().toUpperCase());
        model.addAttribute("product", product);
        model.addAttribute("purchaseOrder", purchaseOrder);
        return "/shopping-cart/detail";
    }


}
