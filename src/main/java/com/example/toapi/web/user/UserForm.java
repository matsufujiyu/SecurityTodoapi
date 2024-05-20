package com.example.toapi.web.user;

import com.example.toapi.web.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UserForm {

    @NotBlank
    @UniqueUsername
    private String userId;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8,max = 12)
    private String password;

    @NotBlank
    private String authority;
}
