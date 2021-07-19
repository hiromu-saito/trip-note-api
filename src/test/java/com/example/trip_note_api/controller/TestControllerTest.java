package com.example.trip_note_api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestControllerTest {
    private MockMvc mockMvc;

    @Autowired
    TestController target;

    @Test
    public  void  test() throws  Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
        var res = mockMvc.perform(get("/"));
        assertEquals("hello world",res.andReturn().getResponse().getContentAsString());
    }
}
