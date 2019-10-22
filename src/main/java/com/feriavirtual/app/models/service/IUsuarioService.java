package com.feriavirtual.app.models.service;

import com.feriavirtual.app.models.entity.Authority;
import com.feriavirtual.app.models.entity.Usuario;

import java.util.List;

public interface IUsuarioService {


    List<Usuario> getAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void delete(Long id);


    List<Authority> getAllAuthorities();
    Authority saveAuthority(Authority authority);
    Authority findAuthorityId(Long id);


}
