package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Category;
import com.feriavirtual.app.models.repository.ICategoryRepository;
import com.feriavirtual.app.models.service.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    public CategoryServiceImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() { return categoryRepository.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) { return categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());}

    @Override
    @Transactional
    public Category save(Category category) { return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) { categoryRepository.delete(findById(id));

    }
}





