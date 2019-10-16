package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.TransportType;

import java.util.List;

public interface ITransportTypeService {

    List<TransportType> getAll();
    TransportType findById(Long id);
    TransportType save(TransportType transportType);
    void delete(Long id);

}
