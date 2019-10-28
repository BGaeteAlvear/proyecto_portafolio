package com.feriavirtual.app.models.service;


import com.feriavirtual.app.models.entity.Authority;
import com.feriavirtual.app.models.entity.Person;
import com.feriavirtual.app.models.entity.Role;
import com.feriavirtual.app.models.entity.Usuario;
import com.feriavirtual.app.models.repository.IPersonRepository;
import com.feriavirtual.app.models.repository.IUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IPersonRepository personRepository;

    private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       /*    Usuario usuario = usuarioRepository.findByUserName(userName);

        if (usuario == null){
            logger.error("Error login");
            throw  new UsernameNotFoundException("Usuario no existe");
        } */

        Person person = personRepository.findByUsername(userName);

        if (person == null) {
            logger.error("Error login");
            throw new UsernameNotFoundException("Usuario no existe");
        }


        /*
        List<GrantedAuthority> grantedAuthorities  = new ArrayList<GrantedAuthority>();
        for (Authority role: usuario.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }*/


        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    //         for (Authority role: usuario.getAuthorities()) {
   //            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        grantedAuthorities.add(new SimpleGrantedAuthority(person.getAuthority().getAuthority()));
 //       }

        Usuario usuario = new Usuario();
        usuario.setUserName(person.getUsername());
        usuario.setPassword(person.getPassword());
        usuario.setEnabled(person.getEnabled());

        logger.info(" --- " + grantedAuthorities.toString());
        return new User(usuario.getUserName(), usuario.getPassword(), usuario.getEnabled(), true, true, true, grantedAuthorities);
     //   return new Person(person.getUsername(), person.getPassword(), true, true, true, true, grantedAuthorities);
    }

}
