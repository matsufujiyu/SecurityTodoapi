package com.example.toapi.web.validation;

import com.example.toapi.auth.UserRepositoriy;
import com.example.toapi.auth.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {

    private final UserRepositoriy userRepositoriy;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepositoriy.findByUsername(value).isEmpty();
    }
}
