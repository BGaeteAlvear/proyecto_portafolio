package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Category;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.entity.ProductAvailable;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product findById(Long id);
    List<Product> findByCategory(Category category);
    Product save(Product product);
    void delete(Long id);
}
