package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<Person, Long > {

    Person findByUsername(String username);
    Optional<Person> findById(Long id);

}
