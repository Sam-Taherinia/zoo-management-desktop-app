package com.zoo.app.dao;

import com.zoo.app.model.animals.Animal;
import com.zoo.app.model.animals.Aquatic;
import com.zoo.app.model.animals.Bird;
import com.zoo.app.model.animals.Wild;
import com.zoo.app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    /**
     * Get all animals from database
     */
    public static List<Animal> getAllAnimals() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Animal animal = createAnimalFromResultSet(rs);
                if (animal != null) {
                    animals.add(animal);
                }
            }
        }
        return animals;
    }

    /**
     * Get animals by type (Bird, Aquatic, Wild)
     */
    public static List<Animal> getAnimalsByType(String type) throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals WHERE type = ? ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, type);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Animal animal = createAnimalFromResultSet(rs);
                    if (animal != null) {
                        animals.add(animal);
                    }
                }
            }
        }
        return animals;
    }

    /**
     * Add a new animal
     */
    public static void addAnimal(Animal animal, String type) throws SQLException {
        String sql = """
            INSERT INTO animals (id, name, type, gender, nationality, national_number, 
                               insurance_situation, food_type, department) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        
        DatabaseConnection.executeUpdate(sql,
            animal.getId(),
            animal.getName(),
            type,
            animal.getGender(),
            animal.getNationality(),
            animal.getNationalNumber(),
            animal.getInsuranceSituation(),
            animal.getFoodType(),
            animal.getDepartment()
        );
    }

    /**
     * Get animals by department
     */
    public static List<Animal> getAnimalsByDepartment(String department) throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals WHERE department = ? ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, department);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Animal animal = createAnimalFromResultSet(rs);
                    if (animal != null) {
                        animals.add(animal);
                    }
                }
            }
        }
        return animals;
    }

    /**
     * Remove animal by id
     */
    public static boolean removeAnimal(String animalId) throws SQLException {
        String sql = "DELETE FROM animals WHERE id = ?";
        int rowsAffected = DatabaseConnection.executeUpdate(sql, animalId);
        return rowsAffected > 0;
    }

    /**
     * Update animal information
     */
    public static void updateAnimal(Animal animal) throws SQLException {
        String sql = """
            UPDATE animals SET 
            name = ?, gender = ?, nationality = ?, national_number = ?, 
            insurance_situation = ?, food_type = ?, department = ? 
            WHERE id = ?
        """;
        
        DatabaseConnection.executeUpdate(sql,
            animal.getName(),
            animal.getGender(),
            animal.getNationality(),
            animal.getNationalNumber(),
            animal.getInsuranceSituation(),
            animal.getFoodType(),
            animal.getDepartment(),
            animal.getId()
        );
    }

    /**
     * Helper method to create specific animal instances from ResultSet
     */
    private static Animal createAnimalFromResultSet(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        
        switch (type.toLowerCase()) {
            case "bird":
                return new Bird(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getString("gender"),
                    rs.getString("nationality"),
                    rs.getString("national_number"),
                    rs.getString("insurance_situation"),
                    rs.getString("food_type"),
                    rs.getString("department")
                );
            case "aquatic":
                return new Aquatic(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getString("gender"),
                    rs.getString("nationality"),
                    rs.getString("national_number"),
                    rs.getString("insurance_situation"),
                    rs.getString("food_type"),
                    rs.getString("department")
                );
            case "wild":
                return new Wild(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getString("gender"),
                    rs.getString("nationality"),
                    rs.getString("national_number"),
                    rs.getString("insurance_situation"),
                    rs.getString("food_type"),
                    rs.getString("department")
                );
            default:
                return null;
        }
    }

    /**
     * Get all birds
     */
    public static List<Bird> getAllBirds() throws SQLException {
        List<Bird> birds = new ArrayList<>();
        List<Animal> animals = getAnimalsByType("Bird");
        for (Animal animal : animals) {
            if (animal instanceof Bird) {
                birds.add((Bird) animal);
            }
        }
        return birds;
    }

    /**
     * Get all aquatic animals
     */
    public static List<Aquatic> getAllAquatics() throws SQLException {
        List<Aquatic> aquatics = new ArrayList<>();
        List<Animal> animals = getAnimalsByType("Aquatic");
        for (Animal animal : animals) {
            if (animal instanceof Aquatic) {
                aquatics.add((Aquatic) animal);
            }
        }
        return aquatics;
    }

    /**
     * Get all wild animals
     */
    public static List<Wild> getAllWilds() throws SQLException {
        List<Wild> wilds = new ArrayList<>();
        List<Animal> animals = getAnimalsByType("Wild");
        for (Animal animal : animals) {
            if (animal instanceof Wild) {
                wilds.add((Wild) animal);
            }
        }
        return wilds;
    }
}
