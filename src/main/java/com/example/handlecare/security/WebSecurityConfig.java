package com.example.handlecare.security;


import com.example.handlecare.entity.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/authorization")
                //TODO разобраться с кастомным успешным
                .successHandler(loginSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home")
        ;

        http.csrf().disable();
        //csrf
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }


    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;

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
                .roles(ADMIN.name())
               // .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails deliverUser = User.builder()
                .username("deliver")
                .password(passwordConfig.passwordEncoder().encode("deliver"))
                 .roles(DELIVER.name())
                //.authorities(DELIVER.getGrantedAuthorities())
                .build();
        UserDetails recipientUser = User.builder()
                .username("recipient")
                .password(passwordConfig.passwordEncoder().encode("recipient"))
                .roles(RECIPIENT.name())
                .build();
        return new InMemoryUserDetailsManager(
                adminUser, deliverUser, recipientUser
        );
    }

}

