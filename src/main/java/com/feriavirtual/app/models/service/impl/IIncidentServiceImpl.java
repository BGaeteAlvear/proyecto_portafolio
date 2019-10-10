package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Incident;
import com.feriavirtual.app.models.repository.IIncidentRepository;
import com.feriavirtual.app.models.service.IIncidentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class IIncidentServiceImpl implements IIncidentService {

    private final IIncidentRepository incidentRepository;

    public IIncidentServiceImpl(IIncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Incident> getAll() {
        return incidentRepository.findAll();
    }

    @Override
    @Transactional
    public Incident save(Incident incident) {
        return incidentRepository.save(incident);
    }

    @Override
    @Transactional(readOnly = true)
    public Incident findById(Long id) {
        return incidentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public void delete(Long id) {
            incidentRepository.delete(findById(id));
    }
}
