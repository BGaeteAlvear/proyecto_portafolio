package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.ProductAvailable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductAvailableRepository extends JpaRepository<ProductAvailable, Long> {
    List<ProductAvailable> findByPerson(Person person);
}
