package com.example.trip_note_api.controller.Memory;

import com.example.trip_note_api.AbstractBaseTest;
import com.example.trip_note_api.ApiExceptionHandler;
import com.example.trip_note_api.domain.dto.Memory;
import com.example.trip_note_api.domain.service.MemoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemoryControllerTest extends AbstractBaseTest {

    MockMvc mockMvc;

    @MockBean
    MemoryService memoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemoryController target;

    @BeforeEach
    public void init() {
        System.out.println("target" + target);
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    public void getAllMemoriesTest() throws Exception {
        List<Memory> memories = new ArrayList<Memory>();
        Memory memory = new Memory();
        memory.setId(1);
        memories.add(memory);
        memories.add(memory);

        doReturn(memories).when(memoryService).getAllMemories("token");
        var resString = mockMvc.perform(get("/memory")
                .header("x-trip-token", "token"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var resJson = objectMapper.readValue(resString, List.class);
        assertEquals(2, resJson.size());

    }

}
