package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostulationRepository extends JpaRepository<Postulation, Long> {

   // List<Postulation> findByPostulation(Tenders tenders);

    List<Postulation> findByTenderId(Long id);


}


