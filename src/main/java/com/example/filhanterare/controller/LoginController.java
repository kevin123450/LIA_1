package com.example.filhanterare.controller;

import com.example.filhanterare.dto.CustomerDTO;
import com.example.filhanterare.dto.LoginRequestDTO;
import com.example.filhanterare.dto.SignUpRequestDTO;
import com.example.filhanterare.dto.WhoAmIDTO;
import com.example.filhanterare.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/login")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {
        RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.OPTIONS
})
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginService.login(loginRequestDTO.username(), loginRequestDTO.password());
    }

    @GetMapping("/whoami")
    public WhoAmIDTO whoAmI(@RequestParam String token) {
        return loginService.whoAmI(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        return loginService.signUp(
                signUpRequestDTO.username(),
                signUpRequestDTO.password(),
                signUpRequestDTO.email(),
                signUpRequestDTO.roles()
        );
    }

    @PostMapping("/customer-signup")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return loginService.customerSignUp(
                customerDTO.id(),
                customerDTO.firstname(),
                customerDTO.lastname(),
                customerDTO.address(),
                customerDTO.zipcode(),
                customerDTO.city(),
                customerDTO.customerType()
        );
    }
}
