package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Incident;

import java.util.List;

public interface IIncidentService {

    List<Incident> getAll();
    Incident save(Incident incident);
    Incident findById(Long id);
    void delete(Long id);

}
