package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.PurchaseOrder;
import com.feriavirtual.app.models.entity.Tenders;

import java.util.List;
import java.util.Optional;

public interface IPurchaseOrderService {

    List<PurchaseOrder> getAll();
    List<PurchaseOrder> getPurchaseOrderByPersonId(Long id);
    PurchaseOrder save(PurchaseOrder purchaseOrder);
    PurchaseOrder getfindById(Long id);
    void delete(Long id);

}
