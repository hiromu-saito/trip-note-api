package com.example.trip_note_api.domain.service;

import com.example.trip_note_api.domain.exception.SignupException;
import com.example.trip_note_api.controller.Auth.LoginForm;
import com.example.trip_note_api.domain.dao.UserDao;
import com.example.trip_note_api.domain.dto.User;
import org.modelmapper.ModelMapper;
import org.seasar.doma.jdbc.UniqueConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserDao userDao;

    @Autowired
    ModelMapper modelMapper;

    public void signup(LoginForm loginForm) {
        User user = modelMapper.map(loginForm, User.class);
        try {
            userDao.insert(user);
        } catch (DuplicateKeyException e) {
            throw new SignupException();
        }
    }
}
