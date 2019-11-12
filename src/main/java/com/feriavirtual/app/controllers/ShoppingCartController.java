package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Category;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.entity.ProductAvailable;
import com.feriavirtual.app.models.service.ICategoryService;
import com.feriavirtual.app.models.service.IProductAvailableService;
import com.feriavirtual.app.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
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
    private IProductService productService;

    @GetMapping("/index")
    public String index (Model model, HttpSession session){
        Person productor =  (Person) session.getAttribute("userSession");
        List<Category> listCategory = categoryService.getAll();
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
        Product product = productService.findById(id);
        model.addAttribute("title_header", "TIENDA DE PRODUCTOS");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | "+product.getName().toUpperCase());
        model.addAttribute("subtitle_header","PRODUCTOS  / "+product.category.getName().toUpperCase()+" / "+product.getName().toUpperCase());
        model.addAttribute("product", product);
        return "/shopping-cart/detail";
    }
}
