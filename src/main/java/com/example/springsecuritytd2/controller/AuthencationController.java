package com.example.springsecuritytd2.controller;

import com.example.springsecuritytd2.dto.AuthenticationRequest;
import com.example.springsecuritytd2.dto.AuthenticationResponse;
import com.example.springsecuritytd2.dto.RegisterRequest;
import com.example.springsecuritytd2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthencationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request
//    ) {
//        System.out.println("sd");
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//// kết quả trả về được đóng gói trong một đối tượng ResponseEntity,
//// với mã trạng thái HTTP là OK (200). Sau đó, kết quả này sẽ được trả về cho người dùng dưới dạng phản hồi HTTP.
//    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        System.out.println("sd");
        return ResponseEntity.ok(authenticationService.authenticate(request));
// kết quả trả về được đóng gói trong một đối tượng ResponseEntity,
// với mã trạng thái HTTP là OK (200). Sau đó, kết quả này sẽ được trả về cho người dùng dưới dạng phản hồi HTTP.
    }

    @GetMapping("/ahihi")
    public String ahihi() {
        return "ahihi";
    }
}
