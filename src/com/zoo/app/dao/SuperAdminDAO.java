package com.zoo.app.dao;

import com.zoo.app.model.people.SuperAdmin;
import com.zoo.app.utils.DatabaseConnection;

import java.sql.*;

public class SuperAdminDAO {

    /**
     * Get SuperAdmin from database
     */
    public static SuperAdmin getSuperAdmin() throws SQLException {
        String sql = "SELECT * FROM superadmin LIMIT 1";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return new SuperAdmin(
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
        return null;
    }

    /**
     * Insert or update SuperAdmin
     */
    public static void saveSuperAdmin(SuperAdmin superAdmin) throws SQLException {
        // Check if SuperAdmin exists
        SuperAdmin existing = getSuperAdmin();
        
        if (existing == null) {
            // Insert new
            String sql = """
                INSERT INTO superadmin (id, name, last_name, username, password, email, phone_number, money) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
            
            DatabaseConnection.executeUpdate(sql,
                superAdmin.getId(),
                superAdmin.getName(),
                superAdmin.getLastName(),
                superAdmin.getUsername(),
                superAdmin.getPassword(),
                superAdmin.getEmailAddress(),
                superAdmin.getPhoneNumber(),
                superAdmin.getMoney()
            );
        } else {
            // Update existing
            String sql = """
                UPDATE superadmin SET 
                name = ?, last_name = ?, username = ?, password = ?, 
                email = ?, phone_number = ?, money = ? 
                WHERE id = ?
            """;
            
            DatabaseConnection.executeUpdate(sql,
                superAdmin.getName(),
                superAdmin.getLastName(),
                superAdmin.getUsername(),
                superAdmin.getPassword(),
                superAdmin.getEmailAddress(),
                superAdmin.getPhoneNumber(),
                superAdmin.getMoney(),
                superAdmin.getId()
            );
        }
    }

    /**
     * Initialize default SuperAdmin if none exists
     */
    public static void initializeDefaultSuperAdmin() throws SQLException {
        SuperAdmin existing = getSuperAdmin();
        if (existing == null) {
            SuperAdmin defaultSuperAdmin = new SuperAdmin(
                "meysam", "13", "taherinia", "Admin", 
                "2018166324", "meysamtaherinia@yahoo.com", 
                "921 604 3785", "13000"
            );
            saveSuperAdmin(defaultSuperAdmin);
        }
    }
}
