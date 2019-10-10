package com.feriavirtual.app;

import com.feriavirtual.app.models.service.JpaUserDetailsService;
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

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.authorizeRequests().antMatchers("/").permitAll()
        http.authorizeRequests().antMatchers("/").hasAnyRole("ADMIN")
                .antMatchers("/css/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").permitAll()
        .and()
        .logout().permitAll()
        ;


    }

    @Autowired
        public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{

        builder.userDetailsService(jpaUserDetailsService)
                .passwordEncoder(passwordEncoder());


        /*
            PasswordEncoder encoder = passwordEncoder();
            User.UserBuilder user = User.builder().passwordEncoder(encoder::encode);

            builder.inMemoryAuthentication()
                    .withUser(user.username("admin").password("12345").roles("ADMIN")); */
        }



}
