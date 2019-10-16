package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Transport;

import java.util.List;

public interface ITransportService {

    List<Transport> getAll();
    Transport findById(Long id);
    Transport save(Transport transport);
    void delete(Long id);

}
