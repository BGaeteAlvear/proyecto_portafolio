package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.PurchaseOrder;
import com.feriavirtual.app.models.entity.Tenders;
import com.feriavirtual.app.models.repository.IPersonRepository;
import com.feriavirtual.app.models.repository.IPurchaseOrderRepository;
import com.feriavirtual.app.models.repository.ITendersRepository;
import com.feriavirtual.app.models.service.IPurchaseOrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

    private final IPurchaseOrderRepository purchaseOrderRepository;
    private final IPersonRepository personRepository;
    private final ITendersRepository tendersRepository;

    public PurchaseOrderServiceImpl(IPurchaseOrderRepository purchaseOrderRepository, IPersonRepository personRepository, ITendersRepository tendersRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.personRepository = personRepository;
        this.tendersRepository = tendersRepository;
    }


    @Override
    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrderByPersonId(Long id) {
        return purchaseOrderRepository.findByPerson(id);
    }

    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder getfindById(Long id) {
       return purchaseOrderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        purchaseOrderRepository.delete(findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Optional<PurchaseOrder> findById(Long id) {
        return purchaseOrderRepository.findById(id);
    }




}
