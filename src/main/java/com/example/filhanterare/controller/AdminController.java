package com.example.filhanterare.controller;

import com.example.filhanterare.dto.AppUserResponseDTO;
import com.example.filhanterare.dto.SignUpRequestDTO;
import com.example.filhanterare.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final LoginService loginService;

    public AdminController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUserResponseDTO> registerUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        return loginService.signUp(
                signUpRequestDTO.username(),
                signUpRequestDTO.password(),
                signUpRequestDTO.email(),
                signUpRequestDTO.roles()
        );
    }
}
