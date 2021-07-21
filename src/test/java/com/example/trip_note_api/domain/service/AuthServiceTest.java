package com.example.trip_note_api.domain.service;


import com.example.trip_note_api.AbstractBaseTest;
import com.example.trip_note_api.controller.Auth.SigninForm;
import com.example.trip_note_api.domain.dao.UserDao;
import com.example.trip_note_api.domain.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class AuthServiceTest extends AbstractBaseTest {
    @MockBean
    UserDao userDao;

    @Autowired
    AuthService authService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void signinSuccessTest() {
        User user = new User();
        user.setUserId(1);
        user.setMailAddress("mailAddress");
        user.setPassword("password");
        user.setDeleteFlag(0);
        doReturn(user).when(userDao).selectByAuthInfo(any(User.class));
        User returnUser = authService.signin(new SigninForm());
        assertEquals(10, returnUser.getToken().length());
        assertEquals(1, returnUser.getUserId());
    }
}
