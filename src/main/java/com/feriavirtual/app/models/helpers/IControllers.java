package com.feriavirtual.app.models.helpers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

public interface IControllers {
    /*
        EJEMPLO ESTRUCTURA CONTROLLER
    =========================================================================
    Verb	  | URI	                   | Action	        | Route Name
    =========================================================================
    GET	      | /photos	               | index	        | photos.index
    GET	      | /photos/form	       | create	        | photos.create
    POST	  | /photos	               | store	        | photos.store
    GET	      | /photos/{photo}	       | show	        | photos.show
    GET	      | /photos/{photo}/edit   | edit	        | photos.edit
    PUT/PATCH |	/photos/{photo}	       | update         | photos.update
    DELETE	  | /photos/{photo}	       | destroy	    | photos.destroy
    =========================================================================
     */

    public String index(Model model);

    public String create(Map<String, Object> model);

    public String store(Object object, BindingResult message, Model model, SessionStatus status);

    public String show(Long id);

    public String edit(Long id, Map<String , Object> model, RedirectAttributes flash);

    public String delete(Long id);


}
