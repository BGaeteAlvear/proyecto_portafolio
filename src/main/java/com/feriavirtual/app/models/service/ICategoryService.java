package com.feriavirtual.app.models.service;


import com.feriavirtual.app.models.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Category findById(Long id);
    Category save(Category category);
    void delete(Long id);


}
