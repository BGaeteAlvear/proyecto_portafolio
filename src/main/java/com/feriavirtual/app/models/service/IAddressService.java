package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Address;

import java.util.List;

public interface IAddressService {

    List<Address> getAll();
    Address save(Address address);
    Address findById(Long id);
    void delete(Long id);



}
