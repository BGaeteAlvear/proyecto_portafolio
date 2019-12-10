package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.PurchaseOrderLocation;

import java.util.List;

public interface IPurchaseOrderLocationService {

    List<PurchaseOrderLocation> getAll();
    PurchaseOrderLocation save(PurchaseOrderLocation purchaseOrderLocation);
    PurchaseOrderLocation getfindById(Long id);
    void delete(Long id);

}
