package com.example.demo.service;

import com.example.demo.model.dto.SensorReadingDTO;
import com.example.demo.model.entity.SensorReadingEntity;
import com.example.demo.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public boolean isAnomaly(SensorReadingEntity reading) {
        if (reading.temperature() < 2 || reading.temperature() > 8) return true;
        if (reading.humidity() > 85) return true;
        return reading.lat() == 0 && reading.lon() == 0;
    }

    public void saveSensorReading(SensorReadingDTO dto) {
        SensorReadingEntity reading = new SensorReadingEntity(
                0,
                dto.containerID(),
                new Timestamp(dto.timestamp()),
                dto.temperature(),
                dto.humidity(),
                dto.lat(),
                dto.lon(),
                false
        );
        final var isAnomaly = isAnomaly(reading);
        sensorRepository.saveSensorReading(reading, isAnomaly);
    }
}
