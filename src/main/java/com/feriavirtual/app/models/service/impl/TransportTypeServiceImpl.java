package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.TransportType;
import com.feriavirtual.app.models.repository.ITransportTypeRepository;
import com.feriavirtual.app.models.service.ITransportTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TransportTypeServiceImpl implements ITransportTypeService {
    private final ITransportTypeRepository transportTypeRepository;

    public TransportTypeServiceImpl(ITransportTypeRepository transportTypeRepository) {
        this.transportTypeRepository = transportTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransportType> getAll() {
        return transportTypeRepository.findAll();
    }

    @Override
    @Transactional
    public TransportType save(TransportType transportType) {
        return transportTypeRepository.save(transportType);
    }

    @Override
    @Transactional(readOnly = true)
    public TransportType findById(Long id) {
        return transportTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        transportTypeRepository.delete(findById(id));
    }
}
