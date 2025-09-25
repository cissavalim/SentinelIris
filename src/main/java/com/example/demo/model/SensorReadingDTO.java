package com.example.demo.model;

public record SensorReadingDTO(
    String containerID,
    long timestamp,
    double temperature,
    double humidity,
    double lat,
    double lon
) {
}