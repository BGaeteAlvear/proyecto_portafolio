package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Person;

import java.util.List;

public interface IPersonService {

    List<Person> getAll();
    Person findById(Long id);
    Person save(Person person);
    void delete(Long id);


}
