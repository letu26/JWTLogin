package com.javaweb.config;

import com.javaweb.components.JwtTokenUtil;
import com.javaweb.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity

public class WebSercurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Value("${api.prefix}")
    private String apiPrefix;
    public WebSercurityConfig(JwtTokenUtil jwtTokenUtil, JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    "/api/users/login",
                                    "/api/users/register"
                            )
                            .permitAll()
                            .requestMatchers(GET, "/api/products**").hasAnyRole("USER", "ADMIN")

                            .requestMatchers(POST, "/api/products/**").hasAnyRole("ADMIN")

                            .requestMatchers(PUT, "/api/products/**").hasAnyRole("ADMIN")

                            .requestMatchers(DELETE,"/api/products/**").hasAnyRole("ADMIN")
                            .anyRequest().authenticated();
                });
        return http.build();
    }
}