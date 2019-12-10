package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Authority;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonService {

    List<Person> getAll();
    Person findById(Long id);
    Person save(Person person);
    void delete(Long id);
    Person findByUsername(String username);



    List<Role> getAllRoles();
    Role saveRoles(Role role);
    Role findRoleById(Long id);

    Authority findAuthorityById(Long id);
    Authority findAuthorityByName (String name);

    Person findPersonByIdParams(Long id);
    Person getById(Long id);

}
