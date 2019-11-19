package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.Category;
import com.feriavirtual.app.models.service.ICategoryService;
import com.feriavirtual.app.models.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.feriavirtual.app.models.service.impl.UploadFileServiceImpl.getFileSizeMegaBytes;

@Controller
@RequestMapping("/category")

@SessionAttributes("category")
public class CategoryController {

    @Autowired
    private IUploadFileService uploadFileService;


    private final ICategoryService categoryService;
    private final Logger log= LoggerFactory.getLogger(getClass());

    public CategoryController(ICategoryService categoryService) { this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public String index (Model model){
        List<Category> listCategories = categoryService.getAll();
        /* DATOS TEMPLATE */
        model.addAttribute("title_header", "CATEGORIAS");
        model.addAttribute("title_page", "PLATAFORMA MAIPO GRANDE | CATEGORIAS");
        model.addAttribute("subtitle_header", "Mantenedor de  Categorias");
        model.addAttribute("list_categories", listCategories);
        return "/category/index";
    }



    @GetMapping("/form")
    public String create(Map<String,Object> model){
        Category category= new Category();
        model.put("title_header", "Crear Categoría");
        model.put("title","Crear Categoría");
        model.put("category", category);
        return "/category/form";
    }

    @PostMapping("/form")
    public String  store(@Valid Category category, BindingResult result, Model model,
                         @RequestParam("file") MultipartFile image, RedirectAttributes flash, SessionStatus status){

        if(!image.isEmpty()){

            String uniqueFilename = UUID.randomUUID().toString()+"_"+image.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFilename);

            Path rootAbsolutePath =rootPath.toAbsolutePath();
            log.info("rootPath: "+ rootPath);
            log.info("rootAbsolutePath: "+ rootAbsolutePath);


                if ((image.getSize()/ (1024 * 1024))<15){

                try {

                    Files.copy(image.getInputStream(), rootAbsolutePath);
                    flash.addFlashAttribute("info", "Se ha cargado correctamente '" + uniqueFilename + "'");
                    category.setImage(uniqueFilename);


                } catch (IOException e) {

                    e.printStackTrace();
                }


            }else{
                    flash.addFlashAttribute("error","Imagen excede el Límite");
                    return "redirect:/category/form";
                }



                if (result.hasErrors()) {
                    model.addAttribute("title", "Crear Categoría");
                    return "form";
                }

                if (category != null) {
                    categoryService.save(category);
                    status.setComplete();
                }
            }
        return "redirect:/category/index";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable(value = "id")Long id, RedirectAttributes flash){
        if (id>0){
            Category category = categoryService.findById(id);
            categoryService.delete(id);

            if(uploadFileService.delete(category.getImage())){
                flash.addFlashAttribute("info", "Imagen: "+ category.getImage()+" eliminada con éxito");
            }
        }
        return "redirect:/category/index";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash){
        Category category = null;
        if(id > 0){
            category = categoryService.findById(id);
            if (category == null){
                flash.addFlashAttribute("error", "El id de la categoría no existe en la base de datos");
                return "redirect:/category/index";
            }
        }else {
            flash.addFlashAttribute("error", "El id de la categoría no puede ser cero");
            return "redirect:/category/index";
        }
        model.put("category", category);
        model.put("title_header", "Editar Categoría");
        model.put("title", "Editar categoría");
        return "/category/form";
    }

}

