package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.IncidentType;
import com.feriavirtual.app.models.service.IIncidentTypeService;
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
@RequestMapping("/incident-type")
@SessionAttributes("incidentType")
public class IncidentTypeController {

    private final IIncidentTypeService incidentTypeService;

    public IncidentTypeController(IIncidentTypeService incidentTypeService) {
        this.incidentTypeService = incidentTypeService;
    }

    @GetMapping("/index")
    public String index(Model model){
        List<IncidentType> list = incidentTypeService.getAll();
        model.addAttribute("title", "Lista de Tipos de Incidentes");
        model.addAttribute("list", list);
        return "/incident-type/index";
    }

    @GetMapping("/form")
    public String create(Map<String, Object> model){
        IncidentType incidentType = new IncidentType();
        model.put("title", "Crear Tipo de Incidente");
        model.put("incidentType", incidentType);
        return "/incident-type/form";
    }

    @PostMapping("/form")
    public String save(@Valid IncidentType incidentType, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash){
        if (incidentType != null){
            incidentTypeService.save(incidentType);
            status.setComplete();
        }
        flash.addFlashAttribute("success", "El tipo de incidente ha sido creado");
        return "redirect:/incident-type/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable (value = "id") Long id){
        if (id > 0){
            incidentTypeService.delete(id);
        }
        return "redirect:/incident-type/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        IncidentType incidentType  = null;
        if(id > 0){
            incidentType = incidentTypeService.findById(id);
            if (incidentType == null){
                flash.addFlashAttribute("error", "El id del tipo de incidente no existe en la base de datos");
                return "redirect:/incident-type/index";
            }
        }else {
            flash.addFlashAttribute("error", "El id del tipo de incidente no puede ser cero");
            return "redirect:/index";
        }
        model.put("incidentType", incidentType);
        model.put("title", "Editar Tipo de Incidente");
        return "/incident-type/form";
    }






}
