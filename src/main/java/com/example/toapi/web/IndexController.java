package com.example.toapi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public  String showLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String showLogoutForm(){
        return "logout";
    }
}
