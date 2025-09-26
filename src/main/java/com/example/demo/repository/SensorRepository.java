package com.example.demo.repository;

import com.example.demo.model.entity.SensorReadingEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class SensorRepository {

    public void saveSensorReading(SensorReadingEntity reading, Boolean isAnomaly) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO SensorReading (ContainerID, Timestamp, Temperature, Humidity, Lat, Lon, AnomalyFlag) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, reading.containerID());
            ps.setTimestamp(2, reading.timestamp());
            ps.setDouble(3, reading.temperature());
            ps.setDouble(4, reading.humidity());
            ps.setDouble(5, reading.lat());
            ps.setDouble(6, reading.lon());
            ps.setBoolean(7, isAnomaly);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving sensor reading", e);
        }
    }
}
