package com.example.filhanterare;

import com.example.filhanterare.dto.LoginRequestDTO;
import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.entities.ERole;
import com.example.filhanterare.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    LoginService loginService;
    LoginRequestDTO loginRequestDTO;
    AppUser appUser;

    @Autowired
    PasswordEncoder passwordEncoder;

   @BeforeEach
    public void setUp(){
       appUser = new AppUser("William", "william@hotmail.com" ,"pass", Set.of(ERole.ADMIN));
    }

    @Test
    public void login_success(){

         loginRequestDTO = new LoginRequestDTO("kevin", "pass");
         Assertions.assertNotNull(loginRequestDTO);
         Assertions.assertEquals(appUser.getEmail(),"salah");



    }
}
