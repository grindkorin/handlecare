package com.example.handlecare.security;


import com.example.handlecare.entity.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.handlecare.entity.enums.Roles.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/administration/**").hasRole(ADMIN.name())
                .mvcMatchers("/deliver/**").hasRole(DELIVER.name())
                .mvcMatchers("/personal/**").hasRole(RECIPIENT.name())

                .anyRequest()
                .permitAll()
                .and()
                .httpBasic();
        //csrf
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }



    //TODO cделать с базы, убрать отсюда
    @Autowired
    PasswordConfig passwordConfig;


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordConfig.passwordEncoder().encode("admin"))
                //.roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails deliverUser = User.builder()
                .username("deliver")
                .password(passwordConfig.passwordEncoder().encode("deliver"))
               // .roles(DELIVER.name())
                .authorities(DELIVER.getGrantedAuthorities())
                .build();
        UserDetails recipientUser = User.builder()
                .username("recipient")
                .password(passwordConfig.passwordEncoder().encode("recipient"))
//                .roles(RECIPIENT.name())
                .authorities(RECIPIENT.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                adminUser, deliverUser, recipientUser
        );
    }
}

