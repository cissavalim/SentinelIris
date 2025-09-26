package com.example.demo.model.entity;

import java.sql.Timestamp;

public record SensorReadingEntity(
    int readingID,
    String containerID,
    Timestamp timestamp,
    double temperature,
    double humidity,
    double lat,
    double lon,
    boolean anomalyFlag
) {}
