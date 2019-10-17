package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
