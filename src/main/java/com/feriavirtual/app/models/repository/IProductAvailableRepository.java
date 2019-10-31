package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.ProductAvailable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductAvailableRepository extends JpaRepository<ProductAvailable, Long> {
}
