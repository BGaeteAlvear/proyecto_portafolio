package com.feriavirtual.app;


import com.feriavirtual.app.auth.handler.LoginSuccessHandler;
import com.feriavirtual.app.models.service.JpaUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private LoginSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


 /*
        //http.authorizeRequests().antMatchers("/").permitAll()
        http
                .authorizeRequests()
                          .antMatchers("/assets/**").permitAll()
                //     .antMatchers("/").hasAnyRole("ADMIN")
                .antMatchers("/").hasAnyRole("ADMIN")
                .antMatchers("/person/**").hasAnyRole("ADMIN")
                //       .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/", true).permitAll()
                .and()
                .logout().permitAll()
        ; */

        http.authorizeRequests().antMatchers("/assets/**", "/css/**", "/js/**", "/images/**").permitAll()
                /*.antMatchers("/ver/**").hasAnyRole("USER")*/
                /*.antMatchers("/uploads/**").hasAnyRole("USER")*/
                /*.antMatchers("/form/**").hasAnyRole("ADMIN")*/
                /*.antMatchers("/eliminar/**").hasAnyRole("ADMIN")*/
                /*.antMatchers("/factura/**").hasAnyRole("ADMIN")*/
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll()
               ;





    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {

/*
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u " +
                        "on (a.usuario_id = u.id)" +
                        "where u.username=?");
*/



        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select username, password, enabled from persons where username=?")
                .authoritiesByUsernameQuery("select p.username, a.authority from persons p join authorities a on (p.authority = a.id) " +
                        "where p.username =?");





/*
       builder.userDetailsService(jpaUserDetailsService)
                .passwordEncoder(passwordEncoder());  */
/*
        PasswordEncoder encoder = passwordEncoder();
        User.UserBuilder user = User.builder().passwordEncoder(encoder::encode);

        builder.inMemoryAuthentication()
                .withUser(user.username("admin").password("12345").roles("ADMIN"))
                .withUser(user.username("Carlos").password("12345").roles("ADMIN"))
                .withUser(user.username("Rodolfo").password("12345").roles("ADMIN"))
                .withUser(user.username("Brian Gaete").password("admin123").roles("ADMIN"))
                .withUser(user.username("Laura").password("12345").roles("ADMIN"))
                .withUser(user.username("Claudio").password("12345").roles("ADMIN"))
                .withUser(user.username("productor").password("12345").roles("PRODUCTOR"))
                .withUser(user.username("transportista").password("12345").roles("TRANSPORTISTA"))
                .withUser(user.username("cliente_ext").password("12345").roles("C_EXTERNO"))
                .withUser(user.username("cliente_int").password("12345").roles("C_INTERNO"))
        ; */


    }
}
