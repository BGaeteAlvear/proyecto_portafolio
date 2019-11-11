package com.feriavirtual.app.controllers;

import com.feriavirtual.app.EmailSenderService;
import com.feriavirtual.app.models.entity.Authority;
import com.feriavirtual.app.models.entity.ConfirmationToken;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Role;
import com.feriavirtual.app.models.repository.IConfirmationTokenRepository;
import com.feriavirtual.app.models.service.IPersonService;
import com.feriavirtual.app.models.service.IUsuarioService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
@SessionAttributes("profile")
public class ProfileController {

    private final IPersonService personService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IUsuarioService usuarioService;

    @Autowired
    private IConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;


    public ProfileController(IPersonService personService, BCryptPasswordEncoder passwordEncoder, IUsuarioService usuarioService) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String index(Map<String, Object> model){

        Person person = new Person();
        List<Role> roles = personService.getAllRoles();
        model.put("title_header", "Crear Usuario");
        model.put("title", "Crear Usuario");
        model.put("listRoles", roles);
        model.put("person", person);
        return "/profile/index";
    }

    @PostMapping("/form")
    public String store(@Valid Person person, BindingResult result, Model model, RedirectAttributes flash){
        Authority authority = new Authority();
        if (person != null){
                try{
                    Role role = personService.findRoleById(person.getRole().getId());
                    System.out.println("++ rol encontrado: "+ role.toString());

                    authority = personService.findAuthorityByName(role.getName());
                    System.out.println("++ authority encontrado: "+ role.toString());

                    person.setAuthority(authority);
                    System.out.println("++ guardado con exito");
                } catch (Exception e){
                    System.out.println("todo se fue a la verga: " + e.toString());
                }
            try{
                // entrega 30 dias para termino de contrato.
                Date dt = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 30);
                dt = c.getTime();

                person.setEndContract(dt);
                person.setEnabled(false);
                person.setPassword(passwordEncoder.encode(person.getPassword()));
                personService.save(person);

                // setting email sender

                ConfirmationToken confirmationToken = new ConfirmationToken(person);
                confirmationTokenRepository.save(confirmationToken);

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(person.getEmail());
                mailMessage.setSubject("Completar registo");
                mailMessage.setFrom("rodolfo.quezada.s@gmail.com");
                mailMessage.setText("Para confirmar la cuenta haz click aca : "
                        +"http://localhost:8080/profile/confirm-account/"+confirmationToken.getConfirmationToken());

                emailSenderService.sendEmail(mailMessage);
                flash.addAttribute("info","Se ha enviado un email para confirmar el perfil");
            }catch (Exception e){
                System.out.println("Error en profile al guardar: " + e.toString());
            }
        } else {
        flash.addAttribute("error","error al crear usuario");
        }
        return "redirect:/";
    }

    @GetMapping("/confirm-account/{token}")
    public String confirmUserAccount(Model model, @PathVariable (value = "token") String confirmationToken){

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        System.out.println("resultado de toke: " + token.toString());
        if (token != null){

            try{
           //     Person person = personService.findByEmail(token.getPerson().getEmail());

                Person person = personService.findById(token.getPerson().getId());
                person.setEnabled(true);
                personService.save(person);
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
        } else{
            System.out.println("Error al confirmar usuario");
        }
        model.addAttribute("title", "Confirmar usuario");
        model.addAttribute("title_header", "Confirmar usuario");
        return "/profile/confirm-account";
    }








}
