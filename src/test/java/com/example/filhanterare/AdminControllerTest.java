package com.example.filhanterare;

import com.example.filhanterare.controller.AdminController;
import com.example.filhanterare.repo.AppUserRepository;
import com.example.filhanterare.service.AppUsersService;
import com.example.filhanterare.service.LoginService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(AdminController.class)
@Import(AdminController.class)
@ContextConfiguration(classes = {
        PasswordEncoder.class,
        AppUserRepository.class,
})
@AutoConfigureMockMvc(addFilters = false)

public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AppUserRepository appUserRepository;

    @MockBean
    private AppUsersService appUsersService;

    @MockBean
    private LoginService loginService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginService).build();
    }


    @Test
    public void createUser() throws Exception{



    }


}
