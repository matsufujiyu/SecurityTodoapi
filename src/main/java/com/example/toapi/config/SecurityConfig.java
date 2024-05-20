package com.example.toapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  {

    private final UserDetailsService userDetailsService;



    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests( authz -> authz
                        .requestMatchers("/h2-console/**").permitAll()
                ).csrf(csrf ->csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions
                                .disable()
                        )
                );

        http

                .authorizeHttpRequests( authz -> authz
                .requestMatchers("/login/**").permitAll()
                                .requestMatchers("/users/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                ).formLogin( formLogin ->formLogin.loginPage("/login"));
        return http.build();
    }


    //protected void configure(AuthenticationManagerBuilder auth)throws Exception{
    //   auth.userDetailsService(userDetailsService)
    //           .passwordEncoder(NoOpPasswordEncoder.getInstance());
    //}

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }





}