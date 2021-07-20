package com.example.trip_note_api.controller;

import com.example.trip_note_api.AbstractBaseTest;
import com.example.trip_note_api.ApiExceptionHandler;
import com.example.trip_note_api.controller.Auth.AuthController;
import com.example.trip_note_api.controller.Auth.LoginForm;
import com.example.trip_note_api.domain.exception.SignupException;
import com.example.trip_note_api.domain.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class AuthControllerTest extends AbstractBaseTest {

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthService mockService;

    @Autowired
    AuthController target;

    LoginForm loginForm;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        loginForm = new LoginForm();
        loginForm.setMailAddress("mailAddress");
        loginForm.setPassword("password");
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    public void success() throws Exception {
        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(loginForm)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void duplicateUserId() throws Exception {
        doThrow(new SignupException()).when(mockService).signup(any(LoginForm.class));
        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(loginForm)))
                .andExpect(status().isConflict());
    }

}
