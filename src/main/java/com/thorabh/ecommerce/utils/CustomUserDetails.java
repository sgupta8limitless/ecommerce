package com.thorabh.ecommerce.utils;

import com.thorabh.ecommerce.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user; // Your User entity

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String[] roles = user.getRole().split(",");

        return Arrays.asList(roles).stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }


}
