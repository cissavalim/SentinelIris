package com.example.demo.model;

import java.sql.Timestamp;

public record SensorReading(
    int readingID,
    String containerID,
    Timestamp timestamp,
    double temperature,
    double humidity,
    double lat,
    double lon,
    boolean anomalyFlag
) {}
