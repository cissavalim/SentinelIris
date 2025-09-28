package com.example.demo.repository;

import com.intersystems.jdbc.IRISDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private final IRISDataSource dataSource;

    private DatabaseConnection() {
        String ip = "iris";
        int port = 1972;
        String namespace = "USER";
        String username = "_SYSTEM";
        String password = "SYS1";
        try {
            dataSource = new IRISDataSource();
            String dbUrl = "jdbc:IRIS://" + ip + ":" + port + "/" + namespace;
            dataSource.setURL(dbUrl);
            dataSource.setUser(username);
            dataSource.setPassword(password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize IRISDataSource", e);
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get connection", e);
        }
    }

}