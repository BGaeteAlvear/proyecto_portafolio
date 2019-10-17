package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
}
