package com.example.trip_note_api.domain.dao;

import com.example.trip_note_api.domain.dto.Memory;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface MemoryDao {

    @Select
    List<Memory> selectByUserId(int userId);

    @Insert (sqlFile = true)
    int insert(Memory memory);

    @Update(include = {"impression","accommodationDate"})
    int update(Memory memory);

    @Update(sqlFile = true)
    int delete(Memory memory);
}
