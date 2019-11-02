package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.service.IPersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {


    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private IPersonService personService;

    @GetMapping("/")
    public String index (Authentication authentication,
                         HttpServletRequest request, Model model, HttpSession session){
        logger.info("Entra en IndexController");
        Person person = null;
        // Datos Usuario
        try{
            User user2 = (User) authentication.getPrincipal();
            person = personService.findByUsername(user2.getUsername());

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "DASHBOARD");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | DASHBOARD");
        model.addAttribute("subtitle_header", "Bienvenido a la plataforma de ventas de frutas Online");
        session.setAttribute("user",person );
        /* DATOS USER */
        model.addAttribute("auth", authentication);


        return "index";
    }

}
