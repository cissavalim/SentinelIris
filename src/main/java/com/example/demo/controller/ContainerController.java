package com.example.demo.controller;

import com.example.demo.model.dto.ContainerDTO;
import com.example.demo.model.entity.SensorReadingEntity;
import com.example.demo.service.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/containers")
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerService containerService;

    @GetMapping("/{containerId}/history")
    public ResponseEntity<List<SensorReadingEntity>> getHistory(@PathVariable("containerId") String containerID) {
        final var list = containerService.findSensorReadingByContainerId(containerID);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{containerId}/status")
    public ResponseEntity<Map<String, Object>> getStatus(@PathVariable("containerId") String containerID) {
        final var status = containerService.findStatusByContainerId(containerID);
        return ResponseEntity.ok(status);
    }

    @PostMapping
    public ResponseEntity<String> saveContainer(@RequestBody ContainerDTO containerDTO) {
        try {
            containerService.saveContainer(containerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Container saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving container: " + e.getMessage());
        }
    }
}
