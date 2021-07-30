package com.example.trip_note_api.controller.Memory;

import com.example.trip_note_api.domain.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memory")
public class MemoryController {

    @Autowired
    MemoryService memoryService;

    @GetMapping
    public ResponseEntity<Object> getAllMemories(@RequestHeader(name = "x-trip-token") String token) {
        var memories = memoryService.getAllMemories(token);
        return ResponseEntity.status(HttpStatus.OK).body(memories);
    }

    @PostMapping
    public ResponseEntity<Object> addMemory(@RequestHeader(name = "x-trip-token") String token,
                                            @RequestBody MemoryForm memoryForm) {
        memoryService.addMemory(memoryForm, token);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateMemory(@PathVariable("id") String id,
                                               @RequestBody MemoryForm memoryForm) {
        memoryService.updateMemory(memoryForm, id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteMemory(
            @PathVariable("id") String id) {
        memoryService.deleteMemory(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
