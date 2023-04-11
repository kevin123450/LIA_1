package com.example.filhanterare;

import com.example.filhanterare.dto.SignUpRequestDTO;
import com.example.filhanterare.entities.ERole;
import com.example.filhanterare.repo.AppUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest(AdminController.class)
@SpringBootTest
/*@Import(AdminController.class)
@ContextConfiguration(classes = {
        PasswordEncoder.class,
        LoginService.class,
        UserDetailsService.class,
        JwtUtil.class
})*/
@AutoConfigureMockMvc(addFilters = false)

public class AdminControllerTest {

    ObjectMapper objectMapper= new ObjectMapper();
    ObjectWriter objectWriter= objectMapper.writer();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserRepository appUserRepository;

    @Test public void createUser() throws Exception {
        SignUpRequestDTO user4 = new SignUpRequestDTO("gunnar", "gunnar@gmail.com", "pass", Set.of(ERole.ADMIN));

       // Mockito.when(appUserRepository.save(user4)).thenReturn(user4);
        String content1 = objectWriter.writeValueAsString(user4);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/admin/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf("application/json"))
                .content(content1)
                .characterEncoding("UTF-8");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username",is("gunnar")));
    }
    @Test
    public void testPost() throws Exception{
        String json =  "{\n"+
                "\"id\":\"4\",\n"+
                "\"username\":\"gunnar\",\n"+
                "\"email\":\"gunnar@gmail.com\",\n"+
                "\"password\":\"pass\",\n"+
                "\"username\":\"Kevin\",\n"+
    "}";
    }

 }
