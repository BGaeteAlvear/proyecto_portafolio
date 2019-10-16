package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransportRepository extends JpaRepository<Transport, Long> {
}
