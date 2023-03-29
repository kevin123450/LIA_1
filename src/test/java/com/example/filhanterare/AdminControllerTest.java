package com.example.filhanterare;

import com.example.filhanterare.controller.AdminController;
import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.entities.ERole;
import com.example.filhanterare.repo.AppUserRepository;
import com.example.filhanterare.service.AppUsersService;
import com.example.filhanterare.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
@Import(AdminController.class)
@ContextConfiguration(classes = {
        PasswordEncoder.class,
        AppUserRepository.class,
})
@AutoConfigureMockMvc(addFilters = false)

public class AdminControllerTest {

    ObjectMapper objectMapper= new ObjectMapper();
    ObjectWriter objectWriter= objectMapper.writer();
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

    @Test public void createUser() throws Exception {
        AppUser user4 = new AppUser(4,"gunnar", "gunnar@gmail.com", "pass", Set.of(ERole.ADMIN));

        Mockito.when(appUserRepository.save(user4)).thenReturn(user4);
        String content1 = objectWriter.writeValueAsString(user4);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/admin/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf("application/json"))
                .content(content1);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username",is("gunnar")));

    }

 }
