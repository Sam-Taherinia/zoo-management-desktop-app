package com.zoo.app.view;

import com.zoo.app.dao.*;
import com.zoo.app.model.animals.Aquatic;
import com.zoo.app.model.animals.Bird;
import com.zoo.app.model.animals.Wild;
import com.zoo.app.model.base.Ticket;
import com.zoo.app.model.people.Manager;
import com.zoo.app.model.people.SuperAdmin;
import com.zoo.app.model.people.Visitor;
import com.zoo.app.model.people.ZooKeeper;
import com.zoo.app.utils.DatabaseConnection;
import com.zoo.app.utils.ZOO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZooApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/sample.fxml"));
        primaryStage.setTitle("Sam's Zoo App");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        try {
            // Initialize database
            DatabaseConnection.initializeDatabase();
            
            // Initialize default SuperAdmin if none exists
            SuperAdminDAO.initializeDefaultSuperAdmin();
            
            // Migrate existing text files if they exist
            migrateFromTextFiles();
            
            // Load data from database to static lists for backward compatibility
            loadAllDataFromDatabase();
            
            System.out.println("Database initialized and data loaded successfully");
            
            launch(args);
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Load all data from database to static ArrayLists for backward compatibility
     */
    public static void loadAllDataFromDatabase() throws SQLException {
        // Load SuperAdmin
        SuperAdmin superAdmin = SuperAdminDAO.getSuperAdmin();
        if (superAdmin != null) {
            ZOO.superadmin = superAdmin;
        }
        
        // Load all lists
        ZOO.managersArrayList.clear();
        ZOO.managersArrayList.addAll(ManagerDAO.getAllManagers());
        
        ZOO.visitorsArrayList.clear();
        ZOO.visitorsArrayList.addAll(VisitorDAO.getAllVisitors());
        
        ZOO.zookeepersArrayList.clear();
        ZOO.zookeepersArrayList.addAll(ZooKeeperDAO.getAllZooKeepers());
        
        ZOO.birdsArrayList.clear();
        ZOO.birdsArrayList.addAll(AnimalDAO.getAllBirds());
        
        ZOO.aquaticsArrayList.clear();
        ZOO.aquaticsArrayList.addAll(AnimalDAO.getAllAquatics());
        
        ZOO.wildsArrayList.clear();
        ZOO.wildsArrayList.addAll(AnimalDAO.getAllWilds());
        
        ZOO.tickets.clear();
        ZOO.tickets.addAll(TicketDAO.getAllTickets());
    }

    /**
     * Migrate data from existing text files to database if files exist
     */
    private static void migrateFromTextFiles() {
        try {
            // Migrate existing data if text files exist
            migrateVisitorsFromTextFile();
            migrateManagersFromTextFile();
            migrateZookeepersFromTextFile();
            migrateSuperAdminFromTextFile();
            migrateTicketsFromTextFile();
            migrateBirdsFromTextFile();
            migrateAquaticsFromTextFile();
            migrateWildsFromTextFile();
        } catch (Exception e) {
            System.out.println("No existing text files found or migration completed: " + e.getMessage());
        }
    }

    // Database-driven write methods (maintain API compatibility)
    public static void writeVisitors() throws SQLException {
        // This method now saves current ArrayList data to database
        for (Visitor visitor : ZOO.visitorsArrayList) {
            try {
                if (!VisitorDAO.usernameExists(visitor.getUsername())) {
                    VisitorDAO.addVisitor(visitor);
                } else {
                    VisitorDAO.updateVisitor(visitor);
                }
            } catch (SQLException e) {
                System.err.println("Error syncing visitor: " + visitor.getUsername());
                throw e;
            }
        }
    }

    public static void writeSuperAdmin() throws SQLException {
        try {
            SuperAdminDAO.saveSuperAdmin(ZOO.superadmin);
        } catch (SQLException e) {
            System.err.println("Error saving SuperAdmin");
            throw e;
        }
    }

    public static void writeManagers() throws SQLException {
        for (Manager manager : ZOO.managersArrayList) {
            try {
                if (!ManagerDAO.usernameExists(manager.getUsername())) {
                    ManagerDAO.addManager(manager);
                } else {
                    ManagerDAO.updateManager(manager);
                }
            } catch (SQLException e) {
                System.err.println("Error syncing manager: " + manager.getUsername());
                throw e;
            }
        }
    }

    public static void writeZookeepers() throws SQLException {
        for (ZooKeeper zookeeper : ZOO.zookeepersArrayList) {
            try {
                if (!ZooKeeperDAO.usernameExists(zookeeper.getUsername())) {
                    ZooKeeperDAO.addZooKeeper(zookeeper);
                } else {
                    ZooKeeperDAO.updateZooKeeper(zookeeper);
                }
            } catch (SQLException e) {
                System.err.println("Error syncing zookeeper: " + zookeeper.getUsername());
                throw e;
            }
        }
    }

    public static void writeTickets() throws SQLException {
        for (Ticket ticket : ZOO.tickets) {
            try {
                TicketDAO.addTicket(ticket);
            } catch (SQLException e) {
                System.err.println("Error syncing ticket for: " + ticket.getOwner());
                throw e;
            }
        }
    }

    public static void writeBirds() throws SQLException {
        for (Bird bird : ZOO.birdsArrayList) {
            try {
                AnimalDAO.addAnimal(bird, "Bird");
            } catch (SQLException e) {
                System.err.println("Error syncing bird: " + bird.getName());
                throw e;
            }
        }
    }

    public static void writeWilds() throws SQLException {
        for (Wild wild : ZOO.wildsArrayList) {
            try {
                AnimalDAO.addAnimal(wild, "Wild");
            } catch (SQLException e) {
                System.err.println("Error syncing wild animal: " + wild.getName());
                throw e;
            }
        }
    }

    public static void writeAquatics() throws SQLException {
        for (Aquatic aquatic : ZOO.aquaticsArrayList) {
            try {
                AnimalDAO.addAnimal(aquatic, "Aquatic");
            } catch (SQLException e) {
                System.err.println("Error syncing aquatic animal: " + aquatic.getName());
                throw e;
            }
        }
    }

    // Migration methods from text files
    private static void migrateVisitorsFromTextFile() {
        try {
            FileReader fr = new FileReader("src/IO files/Visitors.txt"); // TODO
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] str = line.split("     ");
                if (str.length >= 8) {
                    Visitor v = new Visitor(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
                    try {
                        if (!VisitorDAO.usernameExists(v.getUsername())) {
                            VisitorDAO.addVisitor(v);
                        }
                    } catch (SQLException e) {
                        System.err.println("Error migrating visitor: " + v.getUsername());
                    }
                }
            }
            br.close();
            System.out.println("Visitors migrated from text file");
        } catch (IOException e) {
            // File doesn't exist, skip migration
        }
    }

    private static void migrateManagersFromTextFile() {
        try {
            FileReader fr = new FileReader("src/IO files/Managers.txt"); // TODO
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] str = line.split("     ");
                if (str.length >= 7) {
                    Manager m = new Manager(str[0], str[1], str[2], str[3], str[4], str[5], str[6]);
                    try {
                        if (!ManagerDAO.usernameExists(m.getUsername())) {
                            ManagerDAO.addManager(m);
                        }
                    } catch (SQLException e) {
                        System.err.println("Error migrating manager: " + m.getUsername());
                    }
                }
            }
            br.close();
            System.out.println("Managers migrated from text file");
        } catch (IOException e) {
            // File doesn't exist, skip migration
        }
    }

    private static void migrateZookeepersFromTextFile() {
        try {
            FileReader fr = new FileReader("src/IO files/Zookeepers.txt"); // TODO
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] str = line.split("     ");
                if (str.length >= 9) {
                    ZooKeeper z = new ZooKeeper(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8]);
                    try {
                        if (!ZooKeeperDAO.usernameExists(z.getUsername())) {
                            ZooKeeperDAO.addZooKeeper(z);
                        }
                    } catch (SQLException e) {
                        System.err.println("Error migrating zookeeper: " + z.getUsername());
                    }
                }
            }
            br.close();
            System.out.println("ZooKeepers migrated from text file");
        } catch (IOException e) {
            // File doesn't exist, skip migration
        }
    }

    private static void migrateSuperAdminFromTextFile() {
        try {
            FileReader fr = new FileReader("src/IO files/Superadmin.txt"); // TODO
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] str = line.split("     ");
                if (str.length >= 8) {
                    SuperAdmin s = new SuperAdmin(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
                    try {
                        SuperAdminDAO.saveSuperAdmin(s);
                    } catch (SQLException e) {
                        System.err.println("Error migrating SuperAdmin");
                    }
                    break; // Only one SuperAdmin
                }
            }
            br.close();
            System.out.println("SuperAdmin migrated from text file");
        } catch (IOException e) {
            // File doesn't exist, skip migration
        }
    }

    private static void migrateTicketsFromTextFile() {
        try {
            FileReader fr = new FileReader("src/IO files/Tickets.txt"); // TODO
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] str = line.split("     ");
                if (str.length >= 4) {
                    Ticket t = new Ticket(str[0], str[1], str[2], str[3]);
                    try {
                        TicketDAO.addTicket(t);
                    } catch (SQLException e) {
                        System.err.println("Error migrating ticket for: " + t.getOwner());
                    }
                }
            }
            br.close();
            System.out.println("Tickets migrated from text file");
        } catch (IOException e) {
            // File doesn't exist, skip migration
        }
    }

    private static void migrateBirdsFromTextFile() {
        try {
            FileReader fr = new FileReader("src/IO files/Birds.txt"); // TODO
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] str = line.split("     ");
                if (str.length >= 8) {
                    Bird b = new Bird(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
                    try {
                        AnimalDAO.addAnimal(b, "Bird");
                    } catch (SQLException e) {
                        System.err.println("Error migrating bird: " + b.getName());
                    }
                }
            }
            br.close();
            System.out.println("Birds migrated from text file");
        } catch (IOException e) {
            // File doesn't exist, skip migration
        }
    }

    private static void migrateAquaticsFromTextFile() {
        try {
            FileReader fr = new FileReader("src/IO files/Aquatics.txt"); // TODO
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] str = line.split("     ");
                if (str.length >= 8) {
                    Aquatic a = new Aquatic(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
                    try {
                        AnimalDAO.addAnimal(a, "Aquatic");
                    } catch (SQLException e) {
                        System.err.println("Error migrating aquatic: " + a.getName());
                    }
                }
            }
            br.close();
            System.out.println("Aquatic animals migrated from text file");
        } catch (IOException e) {
            // File doesn't exist, skip migration
        }
    }

    private static void migrateWildsFromTextFile() {
        try {
            FileReader fr = new FileReader("src/IO files/Wilds.txt"); // TODO
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] str = line.split("     ");
                if (str.length >= 8) {
                    Wild w = new Wild(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
                    try {
                        AnimalDAO.addAnimal(w, "Wild");
                    } catch (SQLException e) {
                        System.err.println("Error migrating wild animal: " + w.getName());
                    }
                }
            }
            br.close();
            System.out.println("Wild animals migrated from text file");
        } catch (IOException e) {
            // File doesn't exist, skip migration
        }
    }
}
