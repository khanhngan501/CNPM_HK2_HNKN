package com.group08.onlineShop.config;

import com.group08.onlineShop.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/account/**").permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .anyRequest().authenticated()
                );

        return http.build();
    }
}
