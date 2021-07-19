package com.example.trip_note_api.domain.dao;

import com.example.trip_note_api.domain.dto.User;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface UserDao {

    @Select
    User selectByUserId(int userId);

    @Insert(sqlFile = true,exclude = {"deleteFlag"})
    int insert (User user);
}