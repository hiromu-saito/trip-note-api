package com.example.trip_note_api.domain.service;

import com.example.trip_note_api.domain.exception.AuthException;
import com.example.trip_note_api.domain.exception.SignupException;
import com.example.trip_note_api.controller.Auth.SigninForm;
import com.example.trip_note_api.domain.dao.UserDao;
import com.example.trip_note_api.domain.dto.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    UserDao userDao;

    @Autowired
    ModelMapper modelMapper;

    public void signup(SigninForm signinForm) {
        User user = modelMapper.map(signinForm, User.class);
        try {
            userDao.insert(user);
        } catch (DuplicateKeyException e) {
            throw new SignupException("登録済みのメールアドレスです。");
        }
    }

    public User signin(SigninForm signinForm) {
        User user = modelMapper.map(signinForm, User.class);
        var getUser = userDao.selectByAuthInfo(user);
        if (Objects.isNull(getUser)) {
            throw  new AuthException("メールアドレスまたはパスワードが違います。");
        }
        getUser.setToken( RandomStringUtils.randomAlphanumeric(10));
        userDao.updateToken(getUser);
        return getUser;
    }
}
