package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Postulation;
import com.feriavirtual.app.models.entity.PurchaseOrder;
import com.feriavirtual.app.models.entity.Tenders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITendersRepository extends JpaRepository<Tenders, Long> {
    List<Tenders> findByPurchaseOrderAndTendersType(PurchaseOrder purchaseOrder, int tenderType);
    List<Tenders> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
