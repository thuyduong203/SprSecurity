package com.example.springsecuritytd2.service;

import com.example.springsecuritytd2.dto.AuthenticationRequest;
import com.example.springsecuritytd2.dto.AuthenticationResponse;
import com.example.springsecuritytd2.dto.RegisterRequest;
import com.example.springsecuritytd2.entity.User;
import com.example.springsecuritytd2.repository.RoleRepository;
import com.example.springsecuritytd2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .email(registerRequest.getEmail())
                .userName(registerRequest.getUsername())
                .role(roleRepository.findById(registerRequest.getRoleId()).orElse(null))
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        User user1 = user;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        String token = jwtToken;
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
