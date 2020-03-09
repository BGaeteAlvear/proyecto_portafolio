package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.PurchaseOrder;
import com.feriavirtual.app.models.entity.PurchaseOrderLocation;
import com.feriavirtual.app.models.repository.IPurchaseOrderLocationRepository;
import com.feriavirtual.app.models.service.IPurchaseOrderLocationService;
import com.feriavirtual.app.models.service.IPurchaseOrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderLocationServiceImpl implements IPurchaseOrderLocationService {

    private final IPurchaseOrderLocationRepository purchaseOrderLocationRepository;

    public PurchaseOrderLocationServiceImpl(IPurchaseOrderLocationRepository purchaseOrderLocationRepository) {
        this.purchaseOrderLocationRepository = purchaseOrderLocationRepository;
    }

    @Override
    public List<PurchaseOrderLocation> getAll() {
        return purchaseOrderLocationRepository.findAll();
    }

    @Override
    public PurchaseOrderLocation save(PurchaseOrderLocation purchaseOrderLocation) {
        return purchaseOrderLocationRepository.save(purchaseOrderLocation);
    }

    @Override
    public PurchaseOrderLocation getfindById(Long id) {
        return purchaseOrderLocationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        purchaseOrderLocationRepository.delete(findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<PurchaseOrderLocation> findById(Long id) {
        return purchaseOrderLocationRepository.findById(id);
    }

}
