package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.ProductAvailable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
@SessionAttributes("shoppingCart")
public class ShoppingCartController {

    @GetMapping("/list")
    public String list(Model model){
        List<ProductAvailable> productsAvailable = new ArrayList();
        productsAvailable.add(new ProductAvailable(1L, "Durazno", "Descripción Durazno", 1000, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(2L, "Manzana", "Descripción Manzana", 2000, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(3L, "Platano", "Descripción Platano", 3000, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(4L, "Palta", "Descripción Palta", 4000, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));
        productsAvailable.add(new ProductAvailable(5L, "Lechuga", "Descripción Lechuga", 5000, "https://png.pngtree.com/png_detail/20181019/red-apple-kind-png-clipart_1848379.png"));

        model.addAttribute("title", "Carro de Compras");
        model.addAttribute("productsAvailable", productsAvailable);
        return "/shopping-cart/list";
    }

}
