package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.service.IPersonService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/person")
@SessionAttributes("person")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/listar")
    public String listar(Model model){
        List<Person> lista = personService.getAll();
        model.addAttribute("titulo", "Lista Usuarios");
        model.addAttribute("lista", lista);
        return "/person/listar";
    }




    private boolean hasRole(String role) {

        SecurityContext context = SecurityContextHolder.getContext();

        if(context == null) {
            return false;
        }

        Authentication auth = context.getAuthentication();

        if(auth == null) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        return authorities.contains(new SimpleGrantedAuthority(role));

		/*
		 * for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				logger.info("Hola usuario ".concat(auth.getName()).concat(" tu role es: ".concat(authority.getAuthority())));
				return true;
			}
		}

		return false;
		*/

    }




}
