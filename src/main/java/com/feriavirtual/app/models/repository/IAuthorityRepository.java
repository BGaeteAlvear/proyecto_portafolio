package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByAuthority(String name);


}
