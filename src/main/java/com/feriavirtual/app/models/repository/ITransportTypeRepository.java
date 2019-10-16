package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransportTypeRepository extends JpaRepository<TransportType, Long> {
}
