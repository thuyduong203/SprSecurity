package com.example.springsecuritytd2.repository;

import com.example.springsecuritytd2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
