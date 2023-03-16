package com.example.filhanterare;

import com.example.filhanterare.controller.AppUsersController;
import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.entities.ERole;
import com.example.filhanterare.repo.AppUserRepository;
import com.example.filhanterare.service.AppUsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(AppUsersController.class)
public class AppUserControllerTest {

   // @Autowired
   // WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    ObjectMapper objectMapper= new ObjectMapper();
    ObjectWriter objectWriter= objectMapper.writer();

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private AppUsersService appUsersService;

    @InjectMocks
    private AppUsersController appUsersController;

    AppUser user1 = new AppUser("kevin","kevin@hotmail.com","pass", Set.of(ERole.ADMIN));
    AppUser user2 = new AppUser("anders","anders@hotmail.com","pass", Set.of(ERole.ADMIN));
    AppUser user3 = new AppUser("salah","salah@hotmail.com","pass", Set.of(ERole.ADMIN));

    @Before
    public void SetUp(){
       MockitoAnnotations.initMocks(this);
       this.mockMvc= MockMvcBuilders.standaloneSetup(appUsersController).build();
    }

    @Test
    public void getAllUsers_sucess()throws Exception{
        List<AppUser>users = Arrays.asList(user1,user2,user3);

        Mockito.when(appUsersService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].username",is("kevin")));

    }

}
