package com.zoo.app.utils;

import java.sql.*;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:sqlite:zoo.db";
    private static Connection connection;

    /**
     * Get database connection (singleton pattern)
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(DATABASE_URL);
                
                // Enable foreign keys
                try (Statement stmt = connection.createStatement()) {
                    stmt.execute("PRAGMA foreign_keys = ON");
                }
            } catch (ClassNotFoundException e) {
                throw new SQLException("SQLite JDBC driver not found", e);
            }
        }
        return connection;
    }

    /**
     * Initialize database tables
     */
    public static void initializeDatabase() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Create SuperAdmin table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS superadmin (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone_number TEXT,
                    money TEXT DEFAULT '0'
                )
            """);

            // Create Managers table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS managers (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone_number TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            // Create Visitors table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS visitors (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone_number TEXT,
                    money TEXT DEFAULT '0',
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            // Create ZooKeepers table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS zookeepers (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone_number TEXT,
                    department TEXT NOT NULL,
                    income TEXT DEFAULT '0',
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            // Create Departments table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS departments (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL UNIQUE,
                    ticket_price TEXT NOT NULL,
                    type TEXT NOT NULL,
                    zookeeper_count INTEGER DEFAULT 0
                )
            """);

            // Create Animals table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS animals (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    type TEXT NOT NULL,
                    gender TEXT NOT NULL,
                    nationality TEXT,
                    national_number TEXT,
                    insurance_situation TEXT,
                    food_type TEXT,
                    department TEXT NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            // Create Tickets table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tickets (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    price TEXT NOT NULL,
                    owner TEXT NOT NULL,
                    department TEXT NOT NULL,
                    tickets_left TEXT NOT NULL,
                    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            System.out.println("Database tables initialized successfully");
        }
    }

    /**
     * Close database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }

    /**
     * Execute a query and return ResultSet
     */
    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        return stmt.executeQuery();
    }

    /**
     * Execute an update/insert/delete and return number of affected rows
     */
    public static int executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            
            return stmt.executeUpdate();
        }
    }
}
