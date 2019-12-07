package com.feriavirtual.app.controllers;


import com.feriavirtual.app.models.entity.Incident;
import com.feriavirtual.app.models.entity.IncidentType;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.service.IIncidentService;
import com.feriavirtual.app.models.service.IIncidentTypeService;
import com.feriavirtual.app.models.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    private final IIncidentTypeService incidentTypeService;
    private final IPersonService personService;

    public IncidentController(IIncidentService incidentService, IIncidentTypeService incidentTypeService, IPersonService personService) {
        this.incidentService = incidentService;
        this.incidentTypeService = incidentTypeService;
        this.personService = personService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("userSession");
        List<Incident> list = new ArrayList<>();
        if (person.getRole().getId() == 1) {
            list = incidentService.getIncidentsNotAssigned(person.getId());
        }
        if (person.getRole().getId() == 3 || person.getRole().getId() == 4) {
            list = incidentService.getIncidentsByClientId(person.getId());
        }
        //List<Incident> list = incidentService.getAll();
        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "INCIDENTES");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | INCIDENTES");
        model.addAttribute("subtitle_header", "Mantenedor de Incidentes");
        model.addAttribute("list", list);
        model.addAttribute("person", person);

        return "/incident/index";
    }

    @GetMapping("/form")
    public String create(Map<String, Object> model, HttpSession session){
        Incident incident = new Incident();
        Person person = (Person) session.getAttribute("userSession");
        List<IncidentType> incidentTypes = incidentService.getAllTypes();
        model.put("title", "Crear Incidente");
        model.put("incident", incident);
        model.put("incidentTypes", incidentTypes);
        model.put("person", person);
        model.put("editing", false);
        return "/incident/form";
    }

    @PostMapping("/form")
    public String store(@Valid  Incident incident, BindingResult result, Model model, SessionStatus status, HttpSession session, RedirectAttributes flash){
        if (incident != null){
            Person person = (Person) session.getAttribute("userSession");
            if (person.getRole().getId() == 3 || person.getRole().getId() == 4) {
                if (incident.getOrderNumber() < 1) {
                    flash.addFlashAttribute("warning", "El número de orden no puede ser menor a 1.");
                    return "redirect:/incident/form";
                }
                incident.setStatus(true);
                incident.setTransmitter(person);
                incident.setReceiver(null);
                incident.setAnswer(null);
                incidentService.save(incident);
                status.setComplete();
                flash.addFlashAttribute("success", "El incidente ha sido almacenado");
            } else if (person.getRole().getId() == 1) {
                incident.setReceiver(person);
                incident.setStatus(false);
                incidentService.save(incident);
                status.setComplete();
                flash.addFlashAttribute("success", "El incidente ha sido respondido");
            }
        }

        return "redirect:/incident/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if (id > 0){
            incidentService.delete(id);
        }
        flash.addFlashAttribute("warning", "El incidente ha sido eliminado");
        return "redirect:/incident/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash, HttpSession session){
        Incident incident = null;
        List<IncidentType> incidentTypes = incidentTypeService.getAll();
        Person person = (Person) session.getAttribute("userSession");
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
        logger.info(String.valueOf(incident));
        model.put("incidentTypes", incidentTypes);
        model.put("title", "Editar Incidente");
        model.put("person", person);
        model.put("editing", true);

        return "/incident/form";
    }



}
