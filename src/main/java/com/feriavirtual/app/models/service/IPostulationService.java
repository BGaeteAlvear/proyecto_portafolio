package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Postulation;

import java.util.List;

public interface IPostulationService {

    List<Postulation> getAll();
    List<Postulation> geTendersNotAssigned(Long id);
    List<Postulation> getPostulationByTenderId(Long id);
    Postulation save(Postulation postulation);
    Postulation findById(Long id);
    void delete(Long id);

}
