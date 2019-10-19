package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}
