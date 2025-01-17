package com.example.toapi.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomeUserDetails extends User {
    public CustomeUserDetails(String username,String password, Collection<? extends GrantedAuthority> authorities) {
        super(username,  password, authorities);
    }
}
