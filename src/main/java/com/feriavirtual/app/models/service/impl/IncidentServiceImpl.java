package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Incident;
import com.feriavirtual.app.models.entity.IncidentType;
import com.feriavirtual.app.models.repository.IIncidentRepository;
import com.feriavirtual.app.models.repository.IIncidentTypeRepository;
import com.feriavirtual.app.models.service.IIncidentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncidentServiceImpl implements IIncidentService {

    private final IIncidentRepository incidentRepository;
    private final IIncidentTypeRepository incidentTypeRepository;

    public IncidentServiceImpl(IIncidentRepository incidentRepository, IIncidentTypeRepository incidentTypeRepository) {
        this.incidentRepository = incidentRepository;
        this.incidentTypeRepository = incidentTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incident> getAll() {
        return incidentRepository.findAll();
    }

    @Override
    public List<Incident> getIncidentsNotAssigned(Long id) {
        List<Incident> incidents = incidentRepository.findAll(Sort.by(Sort.Direction.DESC, "status"));
        List<Incident> slopes = new ArrayList<>();
        for (Incident incident: incidents) {
            if (incident.getReceiver() == null || incident.getReceiver().getId() == id) {
                slopes.add(incident);
            }
        }
        return slopes;
    }

    @Override
    public List<Incident> getIncidentsByClientId(Long id) {
        List<Incident> incidents = incidentRepository.findAll(Sort.by(Sort.Direction.DESC, "status"));
        List<Incident> clientIncidents = new ArrayList<>();
        for (Incident incident: incidents) {
            if (incident.getTransmitter().getId() == id) {
                clientIncidents.add(incident);
            }
        }
        return clientIncidents;
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

    @Override
    @Transactional(readOnly = true)
    public List<IncidentType> getAllTypes() {
        return incidentTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public IncidentType findTypeById(Long id) {
        return incidentTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public IncidentType saveType(IncidentType incidentType) {
        return incidentTypeRepository.save(incidentType);
    }
}
