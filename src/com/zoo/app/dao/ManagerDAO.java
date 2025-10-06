package com.zoo.app.dao;

import com.zoo.app.model.people.Manager;
import com.zoo.app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

    /**
     * Get all managers from database
     */
    public static List<Manager> getAllManagers() throws SQLException {
        List<Manager> managers = new ArrayList<>();
        String sql = "SELECT * FROM managers ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Manager manager = new Manager(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
                managers.add(manager);
            }
        }
        return managers;
    }

    /**
     * Add a new manager
     */
    public static void addManager(Manager manager) throws SQLException {
        String sql = """
            INSERT INTO managers (id, name, last_name, username, password, email, phone_number) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        
        DatabaseConnection.executeUpdate(sql,
            manager.getId(),
            manager.getName(),
            manager.getLastName(),
            manager.getUsername(),
            manager.getPassword(),
            manager.getEmailAddress(),
            manager.getPhoneNumber()
        );
    }

    /**
     * Remove manager by username
     */
    public static boolean removeManager(String username) throws SQLException {
        String sql = "DELETE FROM managers WHERE username = ?";
        int rowsAffected = DatabaseConnection.executeUpdate(sql, username);
        return rowsAffected > 0;
    }

    /**
     * Find manager by username
     */
    public static Manager findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM managers WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Manager(
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone_number")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Update manager information
     */
    public static void updateManager(Manager manager) throws SQLException {
        String sql = """
            UPDATE managers SET 
            name = ?, last_name = ?, password = ?, email = ?, phone_number = ? 
            WHERE username = ?
        """;
        
        DatabaseConnection.executeUpdate(sql,
            manager.getName(),
            manager.getLastName(),
            manager.getPassword(),
            manager.getEmailAddress(),
            manager.getPhoneNumber(),
            manager.getUsername()
        );
    }

    /**
     * Check if username exists
     */
    public static boolean usernameExists(String username) throws SQLException {
        return findByUsername(username) != null;
    }
}
