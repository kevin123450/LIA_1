package com.example.filhanterare;

import com.example.filhanterare.entities.AppUser;
import com.example.filhanterare.entities.ERole;
import com.example.filhanterare.repo.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
/*@WebMvcTest(AppUsersController.class)
@Import(AppUsersController.class)
@ContextConfiguration(classes = {
        PasswordEncoder.class,
        AppUserRepository.class,
}) */
@AutoConfigureMockMvc(addFilters = false)

public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserRepository appUserRepository;


    AppUser user1 = new AppUser(1,"kevin","kevin@hotmail.com","pass", Set.of(ERole.ADMIN));
    AppUser user2 = new AppUser(2,"anders","anders@hotmail.com","pass", Set.of(ERole.ADMIN));
    AppUser user3 = new AppUser(3,"salah","salah@hotmail.com","pass", Set.of(ERole.USER));

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
                        .get("/api/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.username",is("bob")));
    }
}
