package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Address;
import com.feriavirtual.app.models.service.IAddressService;
import com.feriavirtual.app.models.service.IPersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/address")
@SessionAttributes("address")
public class AddressController {

    private final IAddressService addressService;
    private final IPersonService personService;

    public AddressController(IAddressService addressService, IPersonService personService) {
        this.addressService = addressService;
        this.personService = personService;
    }


    @GetMapping("/index")
    public String index (Model model){

        List<Address> lstAddress = addressService.getAll();

        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "DIRECCIONES");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | DIRECCIONES");
        model.addAttribute("subtitle_header", "Mantenedor de Direcciones");
        model.addAttribute("list_address", lstAddress);

        return "/address/index";
    }




}
