package com.zoo.app.dao;

import com.zoo.app.model.people.Visitor;
import com.zoo.app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorDAO {

    /**
     * Get all visitors from database
     */
    public static List<Visitor> getAllVisitors() throws SQLException {
        List<Visitor> visitors = new ArrayList<>();
        String sql = "SELECT * FROM visitors ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Visitor visitor = new Visitor(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("money")
                );
                visitors.add(visitor);
            }
        }
        return visitors;
    }

    /**
     * Add a new visitor
     */
    public static void addVisitor(Visitor visitor) throws SQLException {
        String sql = """
            INSERT INTO visitors (id, name, last_name, username, password, email, phone_number, money) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        
        DatabaseConnection.executeUpdate(sql,
            visitor.getId(),
            visitor.getName(),
            visitor.getLastName(),
            visitor.getUsername(),
            visitor.getPassword(),
            visitor.getEmailAddress(),
            visitor.getPhoneNumber(),
            visitor.getMoney()
        );
    }

    /**
     * Find visitor by username
     */
    public static Visitor findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM visitors WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Visitor(
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("money")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Update visitor information
     */
    public static void updateVisitor(Visitor visitor) throws SQLException {
        String sql = """
            UPDATE visitors SET 
            name = ?, last_name = ?, password = ?, email = ?, phone_number = ?, money = ? 
            WHERE username = ?
        """;
        
        DatabaseConnection.executeUpdate(sql,
            visitor.getName(),
            visitor.getLastName(),
            visitor.getPassword(),
            visitor.getEmailAddress(),
            visitor.getPhoneNumber(),
            visitor.getMoney(),
            visitor.getUsername()
        );
    }

    /**
     * Remove visitor by username
     */
    public static boolean removeVisitor(String username) throws SQLException {
        String sql = "DELETE FROM visitors WHERE username = ?";
        int rowsAffected = DatabaseConnection.executeUpdate(sql, username);
        return rowsAffected > 0;
    }

    /**
     * Check if username exists
     */
    public static boolean usernameExists(String username) throws SQLException {
        return findByUsername(username) != null;
    }

    /**
     * Update visitor money
     */
    public static void updateVisitorMoney(String username, String newMoney) throws SQLException {
        String sql = "UPDATE visitors SET money = ? WHERE username = ?";
        DatabaseConnection.executeUpdate(sql, newMoney, username);
    }
}
