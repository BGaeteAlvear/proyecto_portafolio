package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.IncidentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIncidentTypeRepository extends JpaRepository<IncidentType, Long>{
}
