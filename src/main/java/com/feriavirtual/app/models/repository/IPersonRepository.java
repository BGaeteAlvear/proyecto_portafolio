package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<Person, Long > {

    Person findByUsername(String username);

    Person getById(Long id);

    @Query(value = "SELECT * FROM Person u WHERE and u.id = :id",
            nativeQuery = true)
    Person findPersonByIdParams(
            @Param("id") Long id);

}
