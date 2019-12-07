package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Transport;
import com.feriavirtual.app.models.entity.TransportType;
import com.feriavirtual.app.models.service.IPersonService;
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

import javax.servlet.http.HttpSession;
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
    private final IPersonService personService;

    public TransportController(ITransportService transportService, ITransportTypeService transportTypeService, IPersonService personService) {
        this.transportService = transportService;
        this.transportTypeService = transportTypeService;
        this.personService = personService;
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
    public String save(@Valid Transport transport, BindingResult result, Model model, SessionStatus status, HttpSession session, RedirectAttributes flash){
        if (transport != null){
            if (transport.getCapacity() == 0 || transport.getCapacity() < 0) {
                flash.addFlashAttribute("warning", "La capacidad debe ser mayor a cero");
                return "redirect:/transport/form";
            }
            Person person = (Person) session.getAttribute("userSession");
            if (person.getRole().getId() == 5) {
                transport.setPerson(person);
                transportService.save(transport);
                status.setComplete();
            } else {
                flash.addFlashAttribute("error", "El usuario no es un transportista");
                return "redirect:/transport/index";
            }
        }
        flash.addFlashAttribute("success", "El transporte ha sido almacenado");
        return "redirect:/transport/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable (value = "id") Long id, RedirectAttributes flash){
        if (id > 0){
            transportService.delete(id);
        }
        flash.addFlashAttribute("warning", "El transporte ha sido eliminado");
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
