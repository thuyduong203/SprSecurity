package com.example.springsecuritytd2.controller;

import com.example.springsecuritytd2.entity.User;
import com.example.springsecuritytd2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class HomeController {
    private final UserRepository userRepository;

    @GetMapping("/get-one-user/{id}")
    public User getOne(@PathVariable("id") String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @GetMapping("/guest")
    public String helloGuest() {
        return "Hello Guest";
    }

    @GetMapping("/admin/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String helloAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/user/hello")
    @PreAuthorize("hasAuthority('USER')")
    public String helloUser() {
        return "Hello User";
    }
}
