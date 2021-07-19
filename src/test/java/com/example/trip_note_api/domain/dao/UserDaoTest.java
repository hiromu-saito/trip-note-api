package com.example.trip_note_api.domain.dao;

import com.example.trip_note_api.AbstractBaseTest;
import com.example.trip_note_api.domain.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest extends AbstractBaseTest {

    @Autowired
    UserDao userDao;

    @Test
    public void selectByUserIdTest() throws  Exception{
        dataSetupByFile("setup/domain/dao/UserDao/selectByUserId.sql");

        User user = userDao.selectByUserId(1);
        assertEquals(Integer.valueOf(1),user.getUserId());
        assertEquals("mailAddress",user.getMailAddress());
        assertEquals("password",user.getPassword());
        assertEquals("token",user.getToken());
        assertEquals(Integer.valueOf(0),user.getDeleteFlag());

    }

    @Test

    public void insertTest() throws Exception {
        dataSetupByFile("setup/domain/dao/UserDao/insert.sql");

        User user = new User();
        user.setUserId(1);
        user.setPassword("password");
        user.setMailAddress("mailAddress");
        userDao.insert(user);

        User selectUser = userDao.selectByUserId(1);
        assertEquals(Integer.valueOf(1),user.getUserId());
        assertEquals("mailAddress",user.getMailAddress());
        assertEquals("password",user.getPassword());
    }

}
