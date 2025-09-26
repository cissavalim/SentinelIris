package com.example.demo.repository;

import com.example.demo.model.entity.ContainerEntity;
import com.example.demo.model.entity.SensorReadingEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContainerRepository {

    public void saveContainer(ContainerEntity containerEntity) {
        String sql = "INSERT INTO Container (ContainerID, Description, Location) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, containerEntity.containerID());
            ps.setString(2, containerEntity.description());
            ps.setString(3, containerEntity.location());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving container", e);
        }
    }


    public List<SensorReadingEntity>  findSensorReadingsByContainerId(final String containerID) {
        List<SensorReadingEntity> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM SensorReading WHERE ContainerID = ? ORDER BY Timestamp DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, containerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SensorReadingEntity(
                        rs.getInt("ReadingID"),
                        rs.getString("ContainerID"),
                        rs.getTimestamp("Timestamp"),
                        rs.getDouble("Temperature"),
                        rs.getDouble("Humidity"),
                        rs.getDouble("Lat"),
                        rs.getDouble("Lon"),
                        rs.getBoolean("AnomalyFlag")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding history", e);
        }
        return list;
    }

    public Map<String, Object> findStatusByContainerId(String containerID) {
        Map<String, Object> status = new HashMap<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM SensorReading WHERE ContainerID = ? ORDER BY Timestamp DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, containerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status.put("containerID", rs.getString("ContainerID"));
                status.put("timestamp", rs.getTimestamp("Timestamp"));
                status.put("temperature", rs.getDouble("Temperature"));
                status.put("humidity", rs.getDouble("Humidity"));
                status.put("lat", rs.getDouble("Lat"));
                status.put("lon", rs.getDouble("Lon"));
                status.put("anomaly", rs.getBoolean("AnomalyFlag"));
            } else {
                status.put("error", "No data found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding status", e);
        }
        return status;
    }
}
