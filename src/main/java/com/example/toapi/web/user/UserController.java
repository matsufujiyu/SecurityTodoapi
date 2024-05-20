package com.example.toapi.web.user;

import com.example.toapi.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "users/list";
    }

    @GetMapping("/registerForm")
    public String showRegisterForm(@ModelAttribute UserForm userForm) {
        return "users/registerForm";
    }

    //POST
    @PostMapping
    public String register(@Validated UserForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return showRegisterForm(form);
        }

        userService.register(form.getUserId(),form.getEmail(),form.getPassword(),form.getAuthority());
        return "redirect:/users";
    }


}
