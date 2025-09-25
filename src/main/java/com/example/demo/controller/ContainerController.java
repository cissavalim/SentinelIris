package com.example.demo.controller;

import com.example.demo.model.SensorReading;
import com.example.demo.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/containers")
public class ContainerController {

    @GetMapping("/{id}/history")
    public ResponseEntity<List<SensorReading>> getHistory(@PathVariable("id") String containerID) {
        List<SensorReading> list = new ArrayList<>();
        try (Connection conn = Repository.getInstance().getConnection()) {
            String sql = "SELECT * FROM SensorReading WHERE ContainerID = ? ORDER BY Timestamp DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, containerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SensorReading(
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
            throw new RuntimeException("Erro ao buscar histórico", e);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> getStatus(@PathVariable("id") String containerID) {
        Map<String, Object> status = new HashMap<>();
        try (Connection conn = Repository.getInstance().getConnection()) {
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
            throw new RuntimeException("Erro ao buscar status", e);
        }
        return ResponseEntity.ok(status);
    }
}
