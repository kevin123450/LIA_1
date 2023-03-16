package com.example.filhanterare;

import com.example.filhanterare.dto.SignUpRequestDTO;
import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    LoginService loginService;

    @Mock
    SignUpRequestDTO signUpRequestDTO;
    AppUser appUser;


    @Test
    public void signUp_success() throws Exception{


    }


}
