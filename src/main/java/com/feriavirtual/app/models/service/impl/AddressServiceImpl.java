package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Address;
import com.feriavirtual.app.models.repository.IAddressRepository;
import com.feriavirtual.app.models.service.IAddressService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {


    private final IAddressRepository addressRepository;

    public AddressServiceImpl(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAll() {
        return  addressRepository.findAll();
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public void delete(Long id) {
        addressRepository.delete(findById(id));
    }
}
