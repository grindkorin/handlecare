package com.example.handlecare.security;


import com.example.handlecare.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserDetailsImpl implements UserDetails {

    private User user;
    private Set<GrantedAuthority> authorities;



    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Roles> roles = Collections.singleton(user.getRole());
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Roles role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.name()));
//        }
//        return authorities;
        return Stream.of(user.getRole()).map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
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

    public String getName() {
        return user.getName();
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
