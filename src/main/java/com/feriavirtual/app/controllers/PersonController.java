package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Authority;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Role;
import com.feriavirtual.app.models.entity.Usuario;
import com.feriavirtual.app.models.service.IPersonService;
import com.feriavirtual.app.models.service.IUsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;
    private final IUsuarioService usuarioService;

    public PersonController(IPersonService personService, BCryptPasswordEncoder passwordEncoder, IUsuarioService usuarioService) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
    }

    /*

    EJEMPLO ESTRUCTURA CONTROLLER
    =========================================================================
    Verb	  | URI	                   | Action	        | Route Name
    =========================================================================
    GET	      | /photos	               | index	        | photos.index
    GET	      | /photos/create	       | create	        | photos.create
    POST	  | /photos	               | store	        | photos.store
    GET	      | /photos/{photo}	       | show	        | photos.show
    GET	      | /photos/{photo}/edit   | edit	        | photos.edit
    PUT/PATCH |	/photos/{photo}	       | update         | photos.update
    DELETE	  | /photos/{photo}	       | destroy	    | photos.destroy
    =========================================================================

       ***

    =========================================================================
    ROLES DE USUARIO
    =========================================================================
    ID |  ROLE
    =========================================================================
    1  | ADMIN
    2  | PRODUCTOR
    3  | CLIENTE EXTERNO
    4  | CLIENTE INTERNO
    5  | TRANSPORTISTA
    6  | CONSULTOR
    7  | SIN ASIGNAR
    =========================================================================

     */




    @GetMapping("/index")
    public String index (Model model){

        List<Person> listUsers = personService.getAll();

        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "USUARIOS");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | USUARIOS");
        model.addAttribute("subtitle_header", "Mantenedor de Usuarios");
        model.addAttribute("list_users", listUsers);

        return "/person/index";
    }

    @GetMapping("/form")
    public String create(Map<String, Object> model){
        Person person = new Person();
        List<Role> roles = personService.getAllRoles();
        model.put("title_header", "Crear Usuario");
        model.put("title", "Crear Usuario");
        model.put("listRoles", roles);
        model.put("person", person);
        return "/person/form";
    }


    @PostMapping("/form")
    public String store(@Valid Person person, BindingResult result, Model model, SessionStatus status){
        Authority authority = new Authority();
        if (person != null){
                if (person.getRole() == null){
                    person.setRole(personService.findRoleById(7l));
                    person.setPassword(passwordEncoder.encode(person.getPassword()));
                } else {

                    System.out.println("--- entra en esta wea");
                    System.out.println("***************  Role " + person.getRole().toString());

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
                    // toda la pega de guardar el autority
                   //  aca tengo q cambiar de la persona el authoriti

                }
                /*
            if (person.getUsername() != null && person.getPassword() != null){
                Usuario usuario = new Usuario();
                Authority authority = new Authority();
               try{
                   usuario.setUserName(person.getUsername());
                   usuario.setPassword(passwordEncoder.encode(person.getPassword()));
                   usuario.setEnabled(false);

                   authority.setAuthority("por asignar");
                   usuario.getAuthorities().add(authority);
                   System.out.println("-- Usuario antes de grabar" + usuario.toString());
                   System.out.println("-- authority antes de grabar" + authority.toString());
                   try{
                       System.out.println("-- Entra al try" + usuario.toString());

                       usuarioService.saveAuthority(authority);
                       usuarioService.save(usuario);
                   }catch (RuntimeException e){
                       System.out.println("-- RuntimeException: "  + e.toString());
                   }

                   System.out.println("- Usuario creado con exito");
               }catch (Exception e){
                   System.out.println("-- Person Controller - guardar usuario Error : " + e.toString());
               }
            } */
            try{
                System.out.println("++ Person: " + person.toString());
                personService.save(person);
                status.setComplete();
            } catch (Exception e){
                System.out.println("-- Person Controller - guardar persona Error : " + e.toString());
            }
            System.out.println("-- Person: " + person.toString());
        }
        return "redirect:/person/index";
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable (value = "id") Long id){
        if (id > 0){
            personService.delete(id);
        }
        return "redirect:/person/index";
    }


    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Person person  = null;
        List<Role> roles = personService.getAllRoles();
        if(id > 0){
         person = personService.findById(id);
            if (person == null){
                flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
                return "redirect:/person/index";
            }
        }else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
            return "redirect:/index";
        }
        model.put("person", person);
        model.put("titulo", "Editar usuario");
        model.put("listRoles", roles);
        return "/person/form";
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form/change-role/{id}")
    public String changeRole(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Person person  = null;
  //      Usuario usuario = null;
        try{
            List<Role> roles = personService.getAllRoles();
            if(id > 0){
                person = personService.findById(id);
                if (person == null){
                    flash.addFlashAttribute("error", "El id de la persona no existe en la base de datos");
                    return "redirect:/person/index";
                }
            }else {
                flash.addFlashAttribute("error", "El id de la persona no puede ser cero");
                return "redirect:/index";
            }

            model.put("roles", roles);
            model.put("person", person);
            model.put("title_header", "Asignar Role");
            model.put("title", "Asignar Role");
        } catch (Exception e){
            System.out.println("- Error : " + e.toString());
        }
        return "/person/change-role";
    }



}
