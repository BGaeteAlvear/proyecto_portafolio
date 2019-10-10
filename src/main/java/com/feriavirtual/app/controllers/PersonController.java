package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.service.IPersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
@SessionAttributes("person")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/listar")
    public String listar(Model model){
        List<Person> lista = personService.getAll();
        model.addAttribute("titulo", "Lista Usuarios");
        model.addAttribute("lista", lista);
        return "/person/listar";
    }

    @GetMapping("/form")
    public String crear(Map<String, Object> model){
        Person person = new Person();
        model.put("titulo", "Crear Usuario");
        model.put("person", person);
        return "/person/form";
    }


    @PostMapping("/form")
    public String guardar(@Valid Person person, BindingResult result, Model model, SessionStatus status){
        if (person != null){
            personService.save(person);
            status.setComplete();
        }
        return "redirect:/person/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable (value = "id") Long id){
        if (id > 0){
            personService.delete(id);
        }
        return "redirect:/person/listar";
    }


    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Person person  = null;
        if(id > 0){
         person = personService.findById(id);
            if (person == null){
                flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
                return "redirect:/person/listar";
            }
        }else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
            return "redirect:/listar";
        }
        model.put("person", person);
        model.put("titulo", "Editar usuario");
        return "/person/form";
    }






}
