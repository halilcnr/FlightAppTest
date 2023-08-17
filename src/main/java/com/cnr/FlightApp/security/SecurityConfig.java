package com.cnr.FlightApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().authenticated() // Require authentication for all requests
                )
                .formLogin(withDefaults()) // Customize login behavior
                .logout(logout -> logout.logoutSuccessUrl("/login")) // Customize logout behavior
                .csrf(AbstractHttpConfigurer::disable); // Disable CSRF for simplicity (customize as needed)

        return http.build();
    }
}
