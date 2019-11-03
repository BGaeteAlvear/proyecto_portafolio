package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.service.IPersonService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginController {

    private final IPersonService personService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login(Model model, Principal principal, RedirectAttributes flash, HttpSession session, Authentication authentication){
        logger.info("Entra a LoginController");

        if (principal != null){
            flash.addFlashAttribute("info", "Ya se a iniciado sesion");
            try{
                User user2 = (User) authentication.getPrincipal();
                Person person = personService.findByUsername(user2.getUsername().toString());
                System.out.println("USUARIO ID : "+person.getId());
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            //Person person = personService.findByUsername(authentication.getCredentials();

            return "redirect:/";
        }
        return "login";
    }


}
