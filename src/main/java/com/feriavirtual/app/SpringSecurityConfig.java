package com.feriavirtual.app;


import com.feriavirtual.app.models.service.JpaUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.info("Entra a  configure en SpringSecurityConfig");

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
        ;


        logger.info("termina a  configure en SpringSecurityConfig");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
   /*     builder.userDetailsService(jpaUserDetailsService)
                .passwordEncoder(passwordEncoder()); */

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
        ;


    }
}
