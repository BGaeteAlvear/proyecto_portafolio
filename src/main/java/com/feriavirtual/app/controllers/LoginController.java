package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.service.JpaUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {


    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(Model model, Principal principal, RedirectAttributes flash){
        logger.info("Entra a LoginController");

        if (principal != null){
            flash.addFlashAttribute("info", "Ya se a iniciado sesion");
            return "redirect:/";
        }
        return "login";
    }


}
