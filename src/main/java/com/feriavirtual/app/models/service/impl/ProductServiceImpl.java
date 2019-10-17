package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.repository.IProductRepository;
import com.feriavirtual.app.models.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl  implements IProductService {

    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() { return productRepository.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) { return productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());}

    @Override
    @Transactional
    public Product save(Product product) { return productRepository.save(product);
    }

    @Override
    public void delete(Long id) { productRepository.delete(findById(id));

    }
}
