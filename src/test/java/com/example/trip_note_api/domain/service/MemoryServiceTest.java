package com.example.trip_note_api.domain.service;

import com.example.trip_note_api.AbstractBaseTest;
import com.example.trip_note_api.controller.Memory.MemoryForm;
import com.example.trip_note_api.domain.dao.MemoryDao;
import com.example.trip_note_api.domain.dao.UserDao;
import com.example.trip_note_api.domain.dto.Memory;
import org.apache.tomcat.jni.Mmap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.seasar.doma.internal.util.AssertionUtil.assertEquals;

public class MemoryServiceTest extends AbstractBaseTest {

    @MockBean
    UserDao userDao;

    @MockBean
    MemoryDao memoryDao;

    @Autowired
    MemoryService memoryService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addMemoryTest() throws Exception {
        String token = "token";
        MemoryForm form = new MemoryForm();
        form.setHotelImage("イメージ");
        form.setImpression("感想");
        form.setHotelName("ホテルネーム");
        form.setAccommodationDate("2020-01-01");

        doReturn(1).when(userDao).getUserIdByToken("token");
        ArgumentCaptor<Memory> memoryCapture = ArgumentCaptor.forClass(Memory.class);
        doReturn(1).when(memoryDao).insert(memoryCapture.capture());

        memoryService.addMemory(form,token);

        Memory captureMemory = memoryCapture.getValue();
        assertEquals(1,captureMemory.getUserId());
        assertEquals("イメージ",captureMemory.getHotelImage());
        assertEquals("感想",captureMemory.getImpression());
        assertEquals("ホテルネーム",captureMemory.getHotelName());
        assertEquals(Date.valueOf("2020-01-01"),captureMemory.getAccommodationDate());
    }
}
