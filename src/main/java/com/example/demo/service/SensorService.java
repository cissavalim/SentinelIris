package com.example.demo.service;

import com.example.demo.model.SensorReading;
import com.example.demo.repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SensorService {
    public boolean isAnomaly(SensorReading reading) {
        // Temperatura fora de [2,8] °C
        if (reading.temperature() < 2 || reading.temperature() > 8) return true;
        // Umidade > 85%
        if (reading.humidity() > 85) return true;
        // GPS ausente (lat/lon == 0)
        return reading.lat() == 0 && reading.lon() == 0;
    }

    public void saveSensorReading(SensorReading reading) {
        try (Connection conn = Repository.getInstance().getConnection()) {
            String sql = "INSERT INTO SensorReading (ContainerID, Timestamp, Temperature, Humidity, Lat, Lon, AnomalyFlag) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, reading.containerID());
            ps.setTimestamp(2, reading.timestamp());
            ps.setDouble(3, reading.temperature());
            ps.setDouble(4, reading.humidity());
            ps.setDouble(5, reading.lat());
            ps.setDouble(6, reading.lon());
            ps.setBoolean(7, isAnomaly(reading));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar leitura do sensor", e);
        }
    }
}
