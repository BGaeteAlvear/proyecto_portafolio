package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.PurchaseOrder;
import com.feriavirtual.app.models.entity.Tenders;

import java.util.List;

public interface ITendersService {

    List<Tenders> getAll();
    List<Tenders> geTendersNotAssigned(Long id);
    List<Tenders> getTendersByPurchaseOrder(Long id);
    Tenders save(Tenders tenders);
    Tenders findById(Long id);
    void delete(Long id);

}
