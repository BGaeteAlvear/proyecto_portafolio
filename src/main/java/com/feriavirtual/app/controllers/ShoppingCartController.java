package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.entity.ProductAvailable;
import com.feriavirtual.app.models.entity.ShoppingCart;
import com.feriavirtual.app.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
@SessionAttributes("shoppingCart")

public class ShoppingCartController {

    @Autowired
    private ShoppingCart shoppingCart;
    List<ShoppingCart> productList = new ArrayList<>();
    //private IProductService productService;


    @GetMapping("/index")
    public String index(Model model) {
        List<ProductAvailable> productsAvailable = new ArrayList();
        productsAvailable.add(new ProductAvailable(1L, "Durazno", "Descripción Durazno", 1000, 150, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(2L, "Manzana", "Descripción Manzana", 2000, 160, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(3L, "Platano", "Descripción Platano", 3000, 170, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(4L, "Palta", "Descripción Palta", 4000, 180, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(5L, "Lechuga", "Descripción Lechuga", 5000, 190, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(5L, "Lechuga", "Descripción Lechuga", 5000, 190, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(5L, "Lechuga", "Descripción Lechuga", 5000, 190, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(5L, "Lechuga", "Descripción Lechuga", 5000, 190, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(5L, "Lechuga", "Descripción Lechuga", 5000, 190, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));

        model.addAttribute("title", "Carro de Compras");
        model.addAttribute("productsAvailable", productsAvailable);
        return "/shopping-cart/index";
    }

    @PostMapping("/add-cart")
    public String addCart(@Valid ProductAvailable productAvailable, int quantity, Model model, RedirectAttributes flash, SessionStatus status, HttpSession session){

        Person customer = (Person) session.getAttribute("user");
        if (session.getAttribute("shoppingCart") != null){
            productList = (List<ShoppingCart>) session.getAttribute("shoppingCart");
        }else{
            productList = new ArrayList<>();
        }

        for (ShoppingCart item : productList) {
            if (productAvailable.getId() == item.getProductAvailableId() && item.getUserId() == customer.getId()){
                item.setQuantity(quantity);
                flash.addFlashAttribute("info", "Producto Actualizado Correctamente");
            }else{
                ShoppingCart itemCart = new ShoppingCart(customer.getId(),productAvailable.getId(),quantity);
                productList.add(itemCart);
                session.setAttribute("shoppingCart",productList);
                flash.addFlashAttribute("info", "Producto Agregado Correctamente");
            }
        }
        model.addAttribute("list-to-cart", productList);
        return "/shopping-cart/add-cart";
    }


    @PostMapping("/remove-item-cart")
    public String removeItemCart( RedirectAttributes flash, Model model, long id, SessionStatus status, HttpSession session){

        Person customer = (Person)session.getAttribute("user");
        List<ShoppingCart> productList = (List<ShoppingCart>) session.getAttribute("shoppingCart");

        for (ShoppingCart item : productList) {
            if (id == item.getProductAvailableId() && item.getUserId() == customer.getId()){
                productList.remove(item);
                flash.addFlashAttribute("info", "Producto Eliminado Correctamente");
            }else{
                flash.addFlashAttribute("error", "Producto buscado, no encontrado");
            }
        }
        model.addAttribute("list-to-cart", productList);

        return "/shopping-cart/update-cart";
    }
    
}
