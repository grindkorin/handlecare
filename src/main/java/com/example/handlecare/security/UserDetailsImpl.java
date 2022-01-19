package com.example.handlecare.security;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.User;
import com.example.handlecare.entity.enums.Roles;
import com.example.handlecare.service.dbServices.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    //TODO UserDto which takes necessary fields from deliver & recipient
    private User user;
    private Set<GrantedAuthority> authorities;



    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Roles> roles = Collections.singleton(user.getRole());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }

    public boolean hasRole(String name) {
        return this.user.getRole().name().equalsIgnoreCase(name);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
