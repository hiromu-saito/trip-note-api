package com.example.trip_note_api.domain.dao;

import com.example.trip_note_api.AbstractBaseTest;
import com.example.trip_note_api.domain.dto.Memory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryDaoTest extends AbstractBaseTest {


    @Autowired
    MemoryDao memoryDao;

    @Test
    public void selectByUserIdTest() throws Exception {
        dataSetupByFile("setup/domain/dao/MemoryDao/selectByUserIdTest.sql");
        var memList = memoryDao.selectByUserId(1);

        assertEquals(2, memList.size());
        var memory = memList.get(0);
        assertEquals("ホテルA", memory.getHotelName());
        assertEquals("イメージA", memory.getHotelImage());
        assertEquals("感想A",memory.getImpression());
        assertEquals(Date.valueOf("2021-07-01"), memory.getAccommodationDate());

    }

    @Test
    public void insertTest() throws Exception{
        dataSetupByFile("setup/domain/dao/MemoryDao/insertTest.sql");
        Memory memory = new Memory();
        memory.setUserId(1);
        memory.setHotelName("ホテルA");
        memory.setHotelImage("イメージA");
        memory.setImpression("感想A");
        memory.setAccommodationDate(Date.valueOf("2021-01-01"));
        memoryDao.insert(memory);

        var getMemory = memoryDao.selectByUserId(1).get(0);
        assertEquals(1,getMemory.getUserId());
        assertEquals(1,getMemory.getId());
        assertEquals("ホテルA",getMemory.getHotelName());
        assertEquals("イメージA",getMemory.getHotelImage());
        assertEquals("感想A",getMemory.getImpression());
        assertEquals(Date.valueOf("2021-01-01"),getMemory.getAccommodationDate());
        assertEquals(0,getMemory.getDeleteFlag());
    }
    @Test
    public void updateTest()throws Exception{
        dataSetupByFile("setup/domain/dao/MemoryDao/updateTest.sql");
        Memory memory = new Memory();
        memory.setUserId(1);
        memory.setId(1);
        memory.setImpression("updated Impression");
        memory.setAccommodationDate(Date.valueOf("2021-01-01"));
        memoryDao.update(memory);

        var getMemory = memoryDao.selectByUserId(1).get(0);
        assertEquals("updated Impression", memory.getImpression());
        assertEquals(Date.valueOf("2021-01-01"),getMemory.getAccommodationDate());
    }

    @Test
    public void deleteTest()throws Exception{
        dataSetupByFile("setup/domain/dao/MemoryDao/deleteTest.sql");
        Memory memory = new Memory();
        memory.setUserId(1);
        memory.setId(1);
        memoryDao.delete(memory);

        var memories = memoryDao.selectByUserId(1);
        assertEquals(0,memories.size());
    }
}
