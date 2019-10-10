package com.feriavirtual.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index (Authentication authentication,
                         HttpServletRequest request, Model model){
        logger.info("Entra en IndexController");
        model.addAttribute("nombreUsuario", authentication.getName());
        model.addAttribute("titulo", "Bienvenido! ");
        return "index";
    }

}
