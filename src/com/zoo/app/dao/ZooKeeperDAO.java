package com.zoo.app.dao;

import com.zoo.app.model.people.ZooKeeper;
import com.zoo.app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZooKeeperDAO {

    /**
     * Get all zookeepers from database
     */
    public static List<ZooKeeper> getAllZooKeepers() throws SQLException {
        List<ZooKeeper> zookeepers = new ArrayList<>();
        String sql = "SELECT * FROM zookeepers ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ZooKeeper zookeeper = new ZooKeeper(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("department"),
                    rs.getString("income")
                );
                zookeepers.add(zookeeper);
            }
        }
        return zookeepers;
    }

    /**
     * Add a new zookeeper
     */
    public static void addZooKeeper(ZooKeeper zookeeper) throws SQLException {
        String sql = """
            INSERT INTO zookeepers (id, name, last_name, username, password, email, phone_number, department, income) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        
        DatabaseConnection.executeUpdate(sql,
            zookeeper.getId(),
            zookeeper.getName(),
            zookeeper.getLastName(),
            zookeeper.getUsername(),
            zookeeper.getPassword(),
            zookeeper.getEmailAddress(),
            zookeeper.getPhoneNumber(),
            zookeeper.getDepartment(),
            zookeeper.getIncome()
        );
    }

    /**
     * Find zookeeper by username
     */
    public static ZooKeeper findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM zookeepers WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ZooKeeper(
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("department"),
                        rs.getString("income")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Get zookeepers by department
     */
    public static List<ZooKeeper> getByDepartment(String department) throws SQLException {
        List<ZooKeeper> zookeepers = new ArrayList<>();
        String sql = "SELECT * FROM zookeepers WHERE department = ? ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, department);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ZooKeeper zookeeper = new ZooKeeper(
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("department"),
                        rs.getString("income")
                    );
                    zookeepers.add(zookeeper);
                }
            }
        }
        return zookeepers;
    }

    /**
     * Update zookeeper information
     */
    public static void updateZooKeeper(ZooKeeper zookeeper) throws SQLException {
        String sql = """
            UPDATE zookeepers SET 
            name = ?, last_name = ?, password = ?, email = ?, phone_number = ?, 
            department = ?, income = ? 
            WHERE username = ?
        """;
        
        DatabaseConnection.executeUpdate(sql,
            zookeeper.getName(),
            zookeeper.getLastName(),
            zookeeper.getPassword(),
            zookeeper.getEmailAddress(),
            zookeeper.getPhoneNumber(),
            zookeeper.getDepartment(),
            zookeeper.getIncome(),
            zookeeper.getUsername()
        );
    }

    /**
     * Remove zookeeper by username
     */
    public static boolean removeZooKeeper(String username) throws SQLException {
        String sql = "DELETE FROM zookeepers WHERE username = ?";
        int rowsAffected = DatabaseConnection.executeUpdate(sql, username);
        return rowsAffected > 0;
    }

    /**
     * Check if username exists
     */
    public static boolean usernameExists(String username) throws SQLException {
        return findByUsername(username) != null;
    }
}
