package com.example.toapi.auth;

import com.example.toapi.config.SecurityConfig;
import com.example.todoapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoriy userRepositoriy;
    private final SecurityConfig securityConfig;



    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Usermodel> findAll(){
        return userRepositoriy.findAll();
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    public void register(String userId, String email, String password,String authority){
       var encodedPassword = passwordEncoder.encode(password);
        userRepositoriy.insert(userId, email, encodedPassword,authority);
    }
}
