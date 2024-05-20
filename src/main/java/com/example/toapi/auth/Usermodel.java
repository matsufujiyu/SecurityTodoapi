package com.example.toapi.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usermodel {

    private String userId;

    private String email;

    private String password;

    private Authority authority;

    public enum Authority{
        ADMIN,
        USER,
    }
}
