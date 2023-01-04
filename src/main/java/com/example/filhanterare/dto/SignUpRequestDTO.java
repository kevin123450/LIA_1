package com.example.filhanterare.dto;

import com.example.filhanterare.entities.ERole;

import java.util.Set;

public record SignUpRequestDTO(String username, String password, String email, Set<ERole> roles) {
}
