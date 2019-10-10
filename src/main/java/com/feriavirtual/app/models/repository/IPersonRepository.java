package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Long > {
}
