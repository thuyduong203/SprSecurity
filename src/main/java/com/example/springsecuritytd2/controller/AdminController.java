package com.example.springsecuritytd2.controller;

import com.example.springsecuritytd2.entity.User;
import com.example.springsecuritytd2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-one-user")
    public User getOne(@RequestParam String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        return user;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> adminSayHello() {
        return ResponseEntity.ok("Hello Admin");
    }
}
