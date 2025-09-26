package com.example.demo.model.dto;

public record SensorReadingDTO(
    String containerID,
    long timestamp,
    double temperature,
    double humidity,
    double lat,
    double lon
) {
}