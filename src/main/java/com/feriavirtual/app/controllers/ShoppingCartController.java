package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.entity.ProductAvailable;
import com.feriavirtual.app.models.entity.ShoppingCart;
import com.feriavirtual.app.models.service.IProductAvailableService;
import com.feriavirtual.app.models.service.IProductService;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopping-cart")
@SessionAttributes("shoppingCart")

public class ShoppingCartController {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    List<ShoppingCart> productList = new ArrayList<>();

    @Autowired
    private IProductAvailableService productAvailableService;

    @GetMapping("/index")
    public String index(Model model) {
        return "/shopping-cart/index";
    }
//
//    @PostMapping("/shopping/add-cart")
//    public Map<String,String> addCart(@Valid ProductAvailable productAvailable, int quantity, Model model, RedirectAttributes flash, SessionStatus status, HttpSession session){
//
//        Person customer = (Person) session.getAttribute("userSession");
//        if (session.getAttribute("shoppingCart") != null){
//            productList = (List<ShoppingCart>) session.getAttribute("shoppingCart");
//        }else{
//            productList = new ArrayList<>();
//        }
//
//        for (ShoppingCart item : productList) {
//            if (productAvailable.getId() == item.getProductAvailableId() && item.getUserId() == customer.getId()){
//                item.setQuantity(quantity);
//            }else{
//                ShoppingCart itemCart = new ShoppingCart(customer.getId(),productAvailable.getId(),quantity);
//                productList.add(itemCart);
//                session.setAttribute("shoppingCart",productList);
//            }
//        }
//        model.addAttribute("list-to-cart", productList);
//
//        Map<String,String> response = new HashMap<>();
//
//        response.put("response","200");
//        response.put("data",productList.toString());
//
//        return response;
//    }

    @RequestMapping(value = "/store/add-item-cart" ,  method = RequestMethod.POST)
    public String estimateWindow(@RequestParam("productID") String productId, @RequestParam("quantity") String quantity,  @RequestParam("message") String recipientRequestText, Model model,HttpSession session) throws JSONException {

        Person customer = (Person) session.getAttribute("userSession");
        productList = null;
        try{
            if (session.getAttribute("shoppingCart") != null){
                productList = (List<ShoppingCart>) session.getAttribute("shoppingCart");
            }else{
                productList = new ArrayList<>();
            }
        }catch (Exception ex){
            productList = new ArrayList<>();
        }
//
        for (ShoppingCart item : productList) {
            if (Long.parseLong(productId) == item.getProductAvailableId() && item.getUserId() == customer.getId()){
                item.setQuantity(Integer.parseInt(quantity));
            }else{
                ShoppingCart itemCart = new ShoppingCart(customer.getId(),Long.parseLong(productId),Integer.parseInt(quantity));
                productList.add(itemCart);
                session.setAttribute("shoppingCart",productList);
            }
        }

        model.addAttribute("list-to-cart", productList);
        JSONObject myJsonObj = new JSONObject();

        myJsonObj.put("status", 200);
        myJsonObj.put("shopping-cart", productList);

        return myJsonObj.toString();
    }
//
//
//    @PostMapping("/remove-item-cart")
//    public String removeItemCart( RedirectAttributes flash, Model model, long id, SessionStatus status, HttpSession session){
//
//        Person customer = (Person)session.getAttribute("user");
//        List<ShoppingCart> productList = (List<ShoppingCart>) session.getAttribute("shoppingCart");
//
//        for (ShoppingCart item : productList) {
//            if (id == item.getProductAvailableId() && item.getUserId() == customer.getId()){
//                productList.remove(item);
//                flash.addFlashAttribute("info", "Producto Eliminado Correctamente");
//            }else{
//                flash.addFlashAttribute("error", "Producto buscado, no encontrado");
//            }
//        }
//        model.addAttribute("list-to-cart", productList);
//
//        return "/shopping-cart/update-cart";
//    }
//
}
