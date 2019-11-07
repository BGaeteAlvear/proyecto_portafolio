package com.feriavirtual.app.controllers;


import com.feriavirtual.app.models.entity.Incident;
import com.feriavirtual.app.models.entity.IncidentType;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.service.IIncidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final IIncidentService incidentService;

    public IncidentController(IIncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/index")
    public String index(Model model){
        List<Incident> list = incidentService.getAll();

        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "INCIDENTES");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | INCIDENTES");
        model.addAttribute("subtitle_header", "Mantenedor de Incidentes");
        model.addAttribute("list", list);

        return "/incident/index";
    }

    @GetMapping("/form")
    public String create(Map<String, Object> model){
        Incident incident = new Incident();
        List<IncidentType> incidentTypes = incidentService.getAllTypes();
        model.put("title", "Crear Incidente");
        model.put("incident", incident);
        model.put("incidentTypes", incidentTypes);
        return "/incident/form";
    }

    @PostMapping("/form")
    public String store(@Valid  Incident incident, BindingResult result, Model model, SessionStatus status){
        if (incident != null){
            incident.setStatus(true);
            incident.setTransmitter("Client");
            incident.setReceiver("Pendiente");
            incidentService.save(incident);
            logger.info(String.valueOf(incident));
            status.setComplete();
        }
        return "redirect:/incident/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        if (id > 0){
            incidentService.delete(id);
        }
        return "redirect:/incident/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Incident incident = null;
        if(id > 0){
            incident = incidentService.findById(id);
            if (incident == null){
                flash.addFlashAttribute("error", "El id del incidente no existe en la base de datos");
                return "redirect:/incident/index";
            }
        }else {
            flash.addFlashAttribute("error", "El id del incidente no puede ser cero");
            return "redirect:/incident/index";
        }
        model.put("incident", incident);
        model.put("title", "Editar Incidente");
        return "/incident/form";
    }



}
