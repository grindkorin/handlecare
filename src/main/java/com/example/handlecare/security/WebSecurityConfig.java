package com.example.handlecare.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.example.handlecare.entity.enums.Roles.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordConfig passwordConfig;
    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/administration/**").hasAnyAuthority(ADMIN.name())
                .mvcMatchers("/deliver/**").hasAnyAuthority(DELIVER.name())
                .mvcMatchers("/personal/**").hasAnyAuthority(RECIPIENT.name())
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/authorization")
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




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService)
               .passwordEncoder(passwordConfig.passwordEncoder());
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordConfig.passwordEncoder());
//        provider.setUserDetailsService(userDetailsService);
//        return provider;
//    }




//
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password(passwordConfig.passwordEncoder().encode("admin"))
//                .roles(ADMIN.name())
//                // .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//        UserDetails deliverUser = User.builder()
//                .username("deliver")
//                .password(passwordConfig.passwordEncoder().encode("deliver"))
//                .roles(DELIVER.name())
//                //.authorities(DELIVER.getGrantedAuthorities())
//                .build();
//        UserDetails recipientUser = User.builder()
//                .username("recipient")
//                .password(passwordConfig.passwordEncoder().encode("recipient"))
//                .roles(RECIPIENT.name())
//                .build();
//        return new InMemoryUserDetailsManager(
//                adminUser, deliverUser, recipientUser
//        );
//    }

}

