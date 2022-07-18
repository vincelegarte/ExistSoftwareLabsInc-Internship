package com.activity.four.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static com.activity.four.security.authorities.ApplicationRole.ADMIN;
import static com.activity.four.security.authorities.ApplicationRole.USER;

public class UserPrincipal implements UserDetails {

    private Users user;
    private Set<SimpleGrantedAuthority> grantedAuthorities;

    @Autowired
    public UserPrincipal(Users user) {
        this.user = user;
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        if (user.getRole().contains("USER")) {
            grantedAuthorities = USER.getGrantedAuthorities();
        }
        if (this.user.getRole().contains("ADMIN")) {
            grantedAuthorities = ADMIN.getGrantedAuthorities();
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
