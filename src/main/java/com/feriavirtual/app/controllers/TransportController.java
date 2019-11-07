package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Transport;
import com.feriavirtual.app.models.entity.TransportType;
import com.feriavirtual.app.models.service.ITransportService;
import com.feriavirtual.app.models.service.ITransportTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/transport")
@SessionAttributes("transport")
public class TransportController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final ITransportService transportService;
    private final ITransportTypeService transportTypeService;

    public TransportController(ITransportService transportService, ITransportTypeService transportTypeService) {
        this.transportService = transportService;
        this.transportTypeService = transportTypeService;
    }

    @GetMapping("/index")
    public String index(Model model){
        List<Transport> list = transportService.getAll();
        model.addAttribute("title", "Lista de Transportes");
        model.addAttribute("list", list);
        return "/transport/index";
    }

    @GetMapping("/form")
    public String create(Map<String, Object> model){
        Transport transport = new Transport();
        List<TransportType> transportTypes = transportTypeService.getAll();
        model.put("title", "Crear Transporte");
        model.put("transport", transport);
        model.put("transportTypes", transportTypes);
        return "/transport/form";
    }

    @PostMapping("/form")
    public String save(@Valid Transport transport, BindingResult result, Model model, SessionStatus status){
        if (transport != null){
            logger.info(String.valueOf(transport));
            transportService.save(transport);
            status.setComplete();
        }
        return "redirect:/transport/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable (value = "id") Long id){
        if (id > 0){
            transportService.delete(id);
        }
        return "redirect:/transport/index";
    }


    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Transport transport  = null;
        List<TransportType> transportTypes = transportTypeService.getAll();
        if(id > 0){
            transport = transportService.findById(id);
            if (transport == null){
                flash.addFlashAttribute("error", "El id del transporte no existe en la base de datos");
                return "redirect:/transport/index";
            }
        }else {
            flash.addFlashAttribute("error", "El id del transporte no puede ser cero");
            return "redirect:/index";
        }
        model.put("transport", transport);
        model.put("transportTypes", transportTypes);
        model.put("title", "Editar Transporte");
        return "/transport/form";
    }

}
