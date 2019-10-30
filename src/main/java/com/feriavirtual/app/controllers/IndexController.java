package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index (Authentication authentication,
                         HttpServletRequest request, Model model, HttpSession session){
        logger.info("Entra en IndexController");

        // Datos Usuario
        Person user = (Person)authentication.getPrincipal();
        session.setAttribute("user", user);

        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "DASHBOARD");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | DASHBOARD");
        model.addAttribute("subtitle_header", "Bienvenido a la plataforma de ventas de frutas Online");

        /* DATOS USER */
        model.addAttribute("auth", authentication);
        model.addAttribute("user", authentication);


        return "index";
    }

}
