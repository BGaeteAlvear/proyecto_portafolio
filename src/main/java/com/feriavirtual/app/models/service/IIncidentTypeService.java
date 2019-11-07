package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.IncidentType;

import java.util.List;

public interface IIncidentTypeService {

    List<IncidentType> getAll();
    IncidentType findById(Long id);
    IncidentType save(IncidentType incidentType);
    void delete(Long id);

}
