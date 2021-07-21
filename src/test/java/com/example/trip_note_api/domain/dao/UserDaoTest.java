package com.example.trip_note_api.domain.dao;

import com.example.trip_note_api.AbstractBaseTest;
import com.example.trip_note_api.domain.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest extends AbstractBaseTest {

    @Autowired
    UserDao userDao;

    User user = new User();

    @BeforeEach
    public void init(){
        user.setUserId(1);
        user.setMailAddress("mailAddress");
        user.setPassword("password");
        user.setDeleteFlag(0);
    }

    @Test
    public void selectByUserIdTest() throws Exception {
        dataSetupByFile("setup/domain/dao/UserDao/selectByUserId.sql");

        User getUser = userDao.selectByUserId(1);
        assertEquals(Integer.valueOf(1), getUser.getUserId());
        assertEquals("mailAddress", getUser.getMailAddress());
        assertEquals("password", getUser.getPassword());
        assertEquals("token", getUser.getToken());
        assertEquals(Integer.valueOf(0), getUser.getDeleteFlag());

    }

    @Test
    public void insertTest() throws Exception {
        dataSetupByFile("setup/domain/dao/UserDao/insert.sql");
        userDao.insert(user);

        User getUser = userDao.selectByUserId(1);
        assertEquals(Integer.valueOf(1), 1);
        assertEquals("mailAddress", getUser.getMailAddress());
        assertEquals("password", getUser.getPassword());
    }

    @Test
    public void selectByAuthInfoTest() throws Exception{
        dataSetupByFile("setup/domain/dao/UserDao/selectByAuthInfo.sql");
        User selectUser = userDao.selectByAuthInfo(user);
        assertEquals(1,selectUser.getUserId());
        assertEquals("password",selectUser.getPassword());
        assertEquals("mailAddress",selectUser.getMailAddress());
    }

    @Test
    public void updateTokenTest()throws Exception{
        dataSetupByFile("setup/domain/dao/UserDao/updateToken.sql");
        user.setToken("token");
        userDao.updateToken(user);

        var getUser = userDao.selectByUserId(1);
        assertEquals("token",getUser.getToken());
    }
}
