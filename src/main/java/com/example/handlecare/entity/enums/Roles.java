package com.example.handlecare.entity.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.handlecare.entity.enums.Permission.*;

@AllArgsConstructor
@Getter
public enum Roles {
    ADMIN(Sets.newHashSet(USER_CHANGE)),
    DELIVER(Sets.newHashSet(ORDER_TAKE, ORDER_CONFIRM)),
    RECIPIENT(Sets.newHashSet(ORDER_POST));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
//        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        permissions.add(new SimpleGrantedAuthority(this.name()));
        return permissions;
    }
}
