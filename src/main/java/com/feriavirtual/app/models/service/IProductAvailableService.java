package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.ProductAvailable;

import java.util.List;

public interface IProductAvailableService {
    List<ProductAvailable> getAll();
    ProductAvailable findById(Long id);
    ProductAvailable save(ProductAvailable productAvailable);
    void delete(Long id);
}
