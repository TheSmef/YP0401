package com.example.YP.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, CHAR;

    @Override
    public String getAuthority() {
        return name();
    }
}
