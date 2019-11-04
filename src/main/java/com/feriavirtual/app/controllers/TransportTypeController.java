package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.TransportType;
import com.feriavirtual.app.models.service.IPersonService;
import com.feriavirtual.app.models.service.ITransportTypeService;
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
@RequestMapping("/transport-type")
@SessionAttributes("transportType")
public class TransportTypeController {

    private final ITransportTypeService transportTypeService;

    public TransportTypeController(ITransportTypeService transportTypeService) {
        this.transportTypeService = transportTypeService;
    }

    @GetMapping("/index")
    public String index(Model model){
        List<TransportType> list = transportTypeService.getAll();
        model.addAttribute("title", "Lista de Tipos de Transportes");
        model.addAttribute("list", list);
        return "/transport-type/index";
    }

    @GetMapping("/form")
    public String create(Map<String, Object> model){
        TransportType transportType = new TransportType();
        model.put("title", "Crear Tipo de Transporte");
        model.put("transportType", transportType);
        return "/transport-type/form";
    }

    @PostMapping("/form")
    public String save(@Valid TransportType transportType, BindingResult result, Model model, SessionStatus status){
        if (transportType != null){
            transportTypeService.save(transportType);
            status.setComplete();
        }
        return "redirect:/transport-type/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable (value = "id") Long id){
        if (id > 0){
            transportTypeService.delete(id);
        }
        return "redirect:/transport-type/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        TransportType transportType  = null;
        if(id > 0){
            transportType = transportTypeService.findById(id);
            if (transportType == null){
                flash.addFlashAttribute("error", "El id del tipo de transporte no existe en la base de datos");
                return "redirect:/transport-type/index";
            }
        }else {
            flash.addFlashAttribute("error", "El id del tipo de transporte no puede ser cero");
            return "redirect:/index";
        }
        model.put("transportType", transportType);
        model.put("title", "Editar Tipo de Transporte");
        return "/transport-type/form";
    }






}
