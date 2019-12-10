package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.PurchaseOrderLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPurchaseOrderLocationRepository extends JpaRepository<PurchaseOrderLocation, Long> {

}
