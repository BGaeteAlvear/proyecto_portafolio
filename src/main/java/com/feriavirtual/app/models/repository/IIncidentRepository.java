package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIncidentRepository extends JpaRepository<Incident, Long> {
}
