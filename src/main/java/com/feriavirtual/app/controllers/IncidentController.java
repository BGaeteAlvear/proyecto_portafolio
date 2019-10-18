package com.feriavirtual.app.controllers;


import com.feriavirtual.app.models.entity.Incident;
import com.feriavirtual.app.models.entity.IncidentType;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.service.IIncidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/incident")
@SessionAttributes("incident")
public class IncidentController {

    private final IIncidentService incidentService;

    public IncidentController(IIncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/index")
    public String listar(Model model){
        List<Incident> listIncidents = incidentService.getAll();

        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "INCIDENTES");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | INCIDENTES");
        model.addAttribute("subtitle_header", "Mantenedor de Incidentes");
        model.addAttribute("listIncidents", listIncidents);

        return "/incident/index";
    }

    @GetMapping("/form")
    public String crear(Map<String, Object> model){
        Incident incident = new Incident();
        List<IncidentType> typeList = incidentService.getAllTypes();
        model.put("titulo", "Crear Inicidente");
        model.put("typeList", typeList);
        model.put("incident", incident);
        return "/incident/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid  Incident incident, BindingResult result, Model model, SessionStatus status){
        if (incident != null){
            incidentService.save(incident);
            status.setComplete();
        }
        return "redirect:/incident/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){
        if (id > 0){
            incidentService.delete(id);
        }
        return "redirect:/incident/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Incident incident = null;
        if(id > 0){
            incident = incidentService.findById(id);
            if (incident == null){
                flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
                return "redirect:/incident/listar";
            }
        }else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
            return "redirect:/incident/listar";
        }
        model.put("incident", incident);
        model.put("titulo", "Editar incidente");
        return "/incident/form";
    }



}
