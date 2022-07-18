package com.activity.four.security.authorities;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.activity.four.security.authorities.ApplicationPermission.*;

public enum ApplicationRole {

    USER(Sets.newHashSet(EMPLOYEE_READ, TICKET_READ)),
    ADMIN(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE, TICKET_READ, ApplicationPermission.TICKET_WRITE));

    private final Set<ApplicationPermission> permissions;

    ApplicationRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority((permission.getPermission())))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
