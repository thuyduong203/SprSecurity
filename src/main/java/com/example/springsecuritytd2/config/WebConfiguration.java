package com.example.springsecuritytd2.config;

import com.example.springsecuritytd2.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf().disable();
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll()
//                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/test/**").permitAll()
//                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/user/**").hasAnyAuthority("USER")
                .anyRequest().authenticated();
//                .requestMatchers("/guest").permitAll()
//                .anyRequest().authenticated();
//                .requestMatchers("/user/**").hasAnyAuthority("USER");
//                .anyRequest().authenticated();
        httpSecurity
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
