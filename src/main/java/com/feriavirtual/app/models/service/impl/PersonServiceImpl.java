package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Role;
import com.feriavirtual.app.models.repository.IPersonRepository;
import com.feriavirtual.app.models.repository.IRoleRepository;
import com.feriavirtual.app.models.service.IPersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    private final IPersonRepository personRepository;
    private final IRoleRepository roleRepository;

    public PersonServiceImpl(IPersonRepository personRepository, IRoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personRepository.delete(findById(id));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRoles(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return  roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
