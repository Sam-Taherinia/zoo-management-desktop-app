package com.zoo.app.dao;

import com.zoo.app.model.base.Ticket;
import com.zoo.app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    /**
     * Get all tickets from database
     */
    public static List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets ORDER BY purchase_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Ticket ticket = new Ticket(
                    rs.getString("price"),
                    rs.getString("owner"),
                    rs.getString("department"),
                    rs.getString("tickets_left")
                );
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    /**
     * Add a new ticket
     */
    public static void addTicket(Ticket ticket) throws SQLException {
        String sql = """
            INSERT INTO tickets (price, owner, department, tickets_left) 
            VALUES (?, ?, ?, ?)
        """;
        
        DatabaseConnection.executeUpdate(sql,
            ticket.getPrice(),
            ticket.getOwner(),
            ticket.getDepartment(),
            ticket.getLeft()
        );
    }

    /**
     * Get tickets by owner
     */
    public static List<Ticket> getTicketsByOwner(String owner) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE owner = ? ORDER BY purchase_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, owner);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ticket ticket = new Ticket(
                        rs.getString("price"),
                        rs.getString("owner"),
                        rs.getString("department"),
                        rs.getString("tickets_left")
                    );
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }

    /**
     * Get tickets by department
     */
    public static List<Ticket> getTicketsByDepartment(String department) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE department = ? ORDER BY purchase_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, department);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ticket ticket = new Ticket(
                        rs.getString("price"),
                        rs.getString("owner"),
                        rs.getString("department"),
                        rs.getString("tickets_left")
                    );
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }

    /**
     * Update ticket information
     */
    public static void updateTicket(int ticketId, String price, String owner, String department, String ticketsLeft) throws SQLException {
        String sql = """
            UPDATE tickets SET 
            price = ?, owner = ?, department = ?, tickets_left = ? 
            WHERE id = ?
        """;
        
        DatabaseConnection.executeUpdate(sql, price, owner, department, ticketsLeft, ticketId);
    }

    /**
     * Remove ticket by ID
     */
    public static boolean removeTicket(int ticketId) throws SQLException {
        String sql = "DELETE FROM tickets WHERE id = ?";
        int rowsAffected = DatabaseConnection.executeUpdate(sql, ticketId);
        return rowsAffected > 0;
    }

    /**
     * Get total tickets sold
     */
    public static int getTotalTicketsSold() throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM tickets";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    /**
     * Get total revenue from tickets
     */
    public static double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(CAST(price AS REAL)) as total FROM tickets";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }
}
