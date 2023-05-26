package com.example.springsecuritytd2.dto;

import com.example.springsecuritytd2.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterRequest {

    private String email;

    private String password;

    private String username;

    private Long roleId;

}
