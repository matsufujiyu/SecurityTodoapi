package com.example.toapi.auth;

import com.example.todoapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomeUserDetailService implements UserDetailsService {

    private final UserRepositoriy userRepositoriy;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return  userRepositoriy.findByUsername(userId)
                .map(
                        usermodel -> new CustomeUserDetails(
                                usermodel.getUserId(),
                                usermodel.getEmail(),
                                toGrantedAuthorityList(usermodel.getAuthority())
                        )
                )
                .orElseThrow(
                        ()->new UsernameNotFoundException(
                                "Given username is not found.(username='" + userId +"')"
                        )
                );

    }

    private List<GrantedAuthority> toGrantedAuthorityList(Usermodel.Authority authority) {
        return Collections.singletonList(new SimpleGrantedAuthority(authority.name()));
    }



}
