package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {


    // List<PurchaseOrder> findByPerson(Person person);

    List<PurchaseOrder> findByPerson(Long id);
}
