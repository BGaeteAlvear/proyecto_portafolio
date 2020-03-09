package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.ProductAvailable;
import com.feriavirtual.app.models.repository.IProductAvailableRepository;
import com.feriavirtual.app.models.service.IProductAvailableService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductAvailableServiceImpl implements IProductAvailableService {

    private final IProductAvailableRepository productAvailableRepository;

    public ProductAvailableServiceImpl(IProductAvailableRepository productAvailableRepository) {
        this.productAvailableRepository = productAvailableRepository;
    }

    @Override
    public List<ProductAvailable> getAll() {
        return productAvailableRepository.findAll();
    }

    @Override
    public ProductAvailable findById(Long id) {
        return productAvailableRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }

    @Override
    public List<ProductAvailable> findByPerson(Person person) {
        return productAvailableRepository.findByPerson(person);
    }

    @Override
    public List<ProductAvailable> findBySaleTypeEquals(String saleType) {
        return productAvailableRepository.findBySaleTypeEquals(saleType);
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
