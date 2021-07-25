package com.example.trip_note_api.domain.service;

import com.example.trip_note_api.controller.Memory.MemoryForm;
import com.example.trip_note_api.domain.dao.MemoryDao;
import com.example.trip_note_api.domain.dao.UserDao;
import com.example.trip_note_api.domain.dto.Memory;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MemoryDao memoryDao;

    @Autowired
    UserDao userDao;

    public List<Memory> getAllMemories(String token) {
        int userId = userDao.getUserIdByToken(token);
        return memoryDao.selectByUserId(userId);
    }

    public void addMemory(MemoryForm memoryForm, String token) {
        var memory = modelMapper.map(memoryForm, Memory.class);
        memory.setUserId(userDao.getUserIdByToken(token));
        memoryDao.insert(memory);
    }

    public void updateMemory(MemoryForm memoryForm, String token, String id) {
        var memory = modelMapper.map(memoryForm, Memory.class);
        memory.setUserId(userDao.getUserIdByToken(token));
        memory.setId(Integer.valueOf(id));
        memoryDao.update(memory);
    }

    public void deleteMemory(String token, String id) {
        Memory memory = new Memory();
        memory.setUserId(userDao.getUserIdByToken(token));
        memory.setId(Integer.valueOf(id));
        memoryDao.delete(memory);
    }
}