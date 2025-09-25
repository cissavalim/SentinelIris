package com.example.demo.controller;

import com.example.demo.model.SensorReading;
import com.example.demo.model.SensorReadingDTO;
import com.example.demo.service.SensorService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService = new SensorService();

    @PostMapping("/data")
    public ResponseEntity<Void> receiveSensorData(@RequestBody SensorReadingDTO dto) {
        SensorReading reading = new SensorReading(
            0,
            dto.containerID(),
            new Timestamp(dto.timestamp()),
            dto.temperature(),
            dto.humidity(),
            dto.lat(),
            dto.lon(),
            false
        );
        sensorService.saveSensorReading(reading);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
}
