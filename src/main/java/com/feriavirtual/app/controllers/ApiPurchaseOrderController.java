package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.PurchaseOrder;
import com.feriavirtual.app.models.repository.IPurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-order")
public class ApiPurchaseOrderController {
    @Autowired
    private IPurchaseOrderRepository purchaseOrderRepository;

    @GetMapping
    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }
}
