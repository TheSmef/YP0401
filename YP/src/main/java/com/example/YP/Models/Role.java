package com.example.YP.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SELLER, ADMIN, CHAR, PURCHASER;

    @Override
    public String getAuthority() {
        return name();
    }
}
