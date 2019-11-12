package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
