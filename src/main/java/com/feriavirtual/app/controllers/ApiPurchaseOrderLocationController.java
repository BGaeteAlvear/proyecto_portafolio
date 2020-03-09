package com.feriavirtual.app.controllers;

import com.feriavirtual.app.models.entity.PurchaseOrderLocation;
import com.feriavirtual.app.models.repository.IPurchaseOrderLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-order-location")
public class ApiPurchaseOrderLocationController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IPurchaseOrderLocationRepository purchaseOrderLocationRepository;

    @GetMapping
    public List<PurchaseOrderLocation> getPurchaseOrderLocations() {
        logger.info(String.valueOf(purchaseOrderLocationRepository.findAll()));
        return purchaseOrderLocationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/save")
    public PurchaseOrderLocation postPurchaseOrderLocation(String code, String roomLocation, String rowLocation, String columnLocation, String positionLocation) {
        logger.info(code);
        logger.info(roomLocation);
        logger.info(rowLocation);
        logger.info(columnLocation);
        logger.info(positionLocation);
        PurchaseOrderLocation purchaseOrderLocation = new PurchaseOrderLocation();
        purchaseOrderLocation.setCode(Integer.parseInt(code));
        purchaseOrderLocation.setRoomLocation(Integer.parseInt(roomLocation));
        purchaseOrderLocation.setRowLocation(Integer.parseInt(rowLocation));
        purchaseOrderLocation.setColumnLocation(Integer.parseInt(columnLocation));
        purchaseOrderLocation.setPositionLocation(Integer.parseInt(positionLocation));

        purchaseOrderLocationRepository.save(purchaseOrderLocation);
        return purchaseOrderLocation;
    }

}
