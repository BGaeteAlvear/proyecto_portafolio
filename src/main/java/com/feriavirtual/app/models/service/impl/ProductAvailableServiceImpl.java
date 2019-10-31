package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.ProductAvailable;
import com.feriavirtual.app.models.repository.IProductAvailableRepository;
import com.feriavirtual.app.models.service.IProductAvailableService;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ProductAvailableServiceImpl implements IProductAvailableService {
    private final IProductAvailableRepository productAvailableRepository;

    public ProductAvailableServiceImpl(IProductAvailableRepository productAvailableRepository) {
        this.productAvailableRepository = productAvailableRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductAvailable> getAll() {
        return productAvailableRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductAvailable findById(Long id) {
        return productAvailableRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
    }

    @Override
    public ProductAvailable save(ProductAvailable productAvailable) {
        return productAvailableRepository.save(productAvailable);
    }

    @Override
    public void delete(Long id) {
        productAvailableRepository.delete(findById(id));

    }
}
