package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.IncidentType;
import com.feriavirtual.app.models.repository.IIncidentTypeRepository;
import com.feriavirtual.app.models.service.IIncidentTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class IncidentTypeServiceImpl implements IIncidentTypeService {
    private final IIncidentTypeRepository incidentTypeRepository;

    public IncidentTypeServiceImpl(IIncidentTypeRepository incidentTypeRepository) {
        this.incidentTypeRepository = incidentTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IncidentType> getAll() {
        return incidentTypeRepository.findAll();
    }

    @Override
    @Transactional
    public IncidentType save(IncidentType incidentType) {
        return incidentTypeRepository.save(incidentType);
    }

    @Override
    @Transactional(readOnly = true)
    public IncidentType findById(Long id) {
        return incidentTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        incidentTypeRepository.delete(findById(id));
    }
}
