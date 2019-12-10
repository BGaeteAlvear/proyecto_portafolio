package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Category;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Product;
import com.feriavirtual.app.models.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByPerson(Long person);
}
