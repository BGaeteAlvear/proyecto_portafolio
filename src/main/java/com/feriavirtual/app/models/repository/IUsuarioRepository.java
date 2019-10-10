package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUserName(String userName);

}
