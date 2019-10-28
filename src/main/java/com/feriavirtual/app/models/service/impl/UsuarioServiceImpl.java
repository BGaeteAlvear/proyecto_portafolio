package com.feriavirtual.app.models.service.impl;

import com.feriavirtual.app.models.entity.Authority;
import com.feriavirtual.app.models.entity.Usuario;
import com.feriavirtual.app.models.repository.IAuthorityRepository;
import com.feriavirtual.app.models.repository.IUsuarioRepository;
import com.feriavirtual.app.models.service.IUsuarioService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IAuthorityRepository authorityRepository;

    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository, IAuthorityRepository authorityRepository) {
        this.usuarioRepository = usuarioRepository;
        this.authorityRepository = authorityRepository;
    }


    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.delete(findById(id));
    }

    @Override
    public Usuario findByUserName(String userName) {
        return usuarioRepository.findByUserName(userName);
    }

    @Override
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findAuthorityId(Long id) {
        return authorityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
