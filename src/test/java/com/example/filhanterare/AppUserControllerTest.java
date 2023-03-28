package com.example.filhanterare;

import com.example.filhanterare.controller.AdminController;
import com.example.filhanterare.controller.AppUsersController;
import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.entities.ERole;
import com.example.filhanterare.repo.AppUserRepository;
import com.example.filhanterare.service.AppUsersService;
import com.example.filhanterare.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppUsersController.class)
@Import(AppUsersController.class)
@ContextConfiguration(classes = {
        PasswordEncoder.class,
        AppUserRepository.class,
})
@AutoConfigureMockMvc(addFilters = false)

public class AppUserControllerTest {

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

    AppUser user1 = new AppUser(1,"kevin","kevin@hotmail.com","pass", Set.of(ERole.ADMIN));
    AppUser user2 = new AppUser(2,"anders","anders@hotmail.com","pass", Set.of(ERole.ADMIN));
    AppUser user3 = new AppUser(3,"salah","salah@hotmail.com","pass", Set.of(ERole.USER));

    @Before
    public void setUp()throws  Exception{
        initMocks(this);
        AdminController adminController = new AdminController(loginService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void getAllUsers_sucess()throws Exception{
        List<AppUser>users = Arrays.asList(user1,user2,user3);
        Mockito.when(appUsersService.getAllUsers()).thenReturn(List.of(user1,user2,user3));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].username",is("kevin")))
                .andExpect(jsonPath("$[0].email",is("kevin@hotmail.com")));
        }

    @Test
    public void deleteUser()throws Exception{
        Mockito.when(appUserRepository.findAppUserById(user1.getId())).thenReturn(Optional.of(user1));
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        System.out.println(user1.getId());

    }

    @Test
    public void getUserbyID() throws Exception {
        var user11 = new AppUser(1,"bob","bob@gmail.com","pass",Set.of(ERole.ADMIN));
        Mockito.when(appUserRepository.findAppUserById(user11.getId())).thenReturn(Optional.of(user11));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$[0].username",is("kevin")));
    }
    @Test public void createUser() throws Exception {
        AppUser user4 = new AppUser("gunnar", "gunnar@gmail.com", "pass", Set.of(ERole.ADMIN));
        Mockito.when(appUserRepository.save(user4)).thenReturn(user4);
        String content = objectWriter.writeValueAsString(user4);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/admin/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.username",is("gunnar")));

    }
}
