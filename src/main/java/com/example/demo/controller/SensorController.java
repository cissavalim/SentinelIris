package com.example.demo.controller;

import com.example.demo.model.dto.SensorReadingDTO;
import com.example.demo.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PostMapping("/data")
    public ResponseEntity<Void> receiveSensorData(@RequestBody SensorReadingDTO dto) {
        sensorService.saveSensorReading(dto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
}
