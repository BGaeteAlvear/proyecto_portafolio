package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Authority;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Role;
import com.feriavirtual.app.models.repository.IAuthorityRepository;
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
    private final IAuthorityRepository authorityRepository;

    public PersonServiceImpl(IPersonRepository personRepository, IRoleRepository roleRepository, IAuthorityRepository authorityRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
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
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
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

    @Override
    public Authority findAuthorityById(Long id) {
        return authorityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Authority findAuthorityByName(String name) {
        return authorityRepository.findByAuthority(name);
    }
}
