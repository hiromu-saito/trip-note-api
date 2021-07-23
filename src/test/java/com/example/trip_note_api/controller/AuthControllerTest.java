package com.example.trip_note_api.controller;

import com.example.trip_note_api.AbstractBaseTest;
import com.example.trip_note_api.ApiExceptionHandler;
import com.example.trip_note_api.controller.Auth.AuthController;
import com.example.trip_note_api.controller.Auth.SigninForm;
import com.example.trip_note_api.domain.dto.User;
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

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class AuthControllerTest extends AbstractBaseTest {

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthService mockService;

    @Autowired
    AuthController target;

    SigninForm signinForm;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        signinForm = new SigninForm();
        signinForm.setMailAddress("mailAddress");
        signinForm.setPassword("password");
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    public void success() throws Exception {
        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(signinForm)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void duplicateUserId() throws Exception {
        doThrow(new SignupException("登録済みのメールアドレスです。")).when(mockService).signup(any(SigninForm.class));
        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(signinForm)))
                .andExpect(status().isConflict());
    }

    @Test
    public  void signinSuccessTest() throws Exception{
        User user = new User();
        user.setUserId(1);
        user.setToken("token");
        doReturn(user).when(mockService).signin(any(SigninForm.class));
        mockMvc.perform(put("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(signinForm)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.token").value("token"));

    }

}
