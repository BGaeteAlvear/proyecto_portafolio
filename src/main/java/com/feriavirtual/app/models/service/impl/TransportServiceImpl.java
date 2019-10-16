package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Transport;
import com.feriavirtual.app.models.repository.ITransportRepository;
import com.feriavirtual.app.models.service.ITransportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TransportServiceImpl implements ITransportService {
    private final ITransportRepository transportRepository;

    public TransportServiceImpl(ITransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transport> getAll() {
        return transportRepository.findAll();
    }

    @Override
    @Transactional
    public Transport save(Transport transport) {
        return transportRepository.save(transport);
    }

    @Override
    @Transactional(readOnly = true)
    public Transport findById(Long id) {
        return transportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        transportRepository.delete(findById(id));
    }
}
