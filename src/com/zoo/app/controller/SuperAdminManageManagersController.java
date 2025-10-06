package com.zoo.app.controller;

import com.zoo.app.dao.ManagerDAO;
import com.zoo.app.model.people.Manager;
import com.zoo.app.utils.ZOO;
import com.zoo.app.view.ZooApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

import static com.zoo.app.utils.ZOO.managersArrayList;

public class SuperAdminManageManagersController {

    // Add Manager tab fields
    @FXML private TextField tf_reg_username;
    @FXML private PasswordField pf_reg_pass;
    @FXML private PasswordField pf_reg_passconf;
    @FXML private TextField tf_reg_email;
    @FXML private TextField tf_reg_name;
    @FXML private TextField tf_reg_lastname;

    // Remove Manager tab fields
    @FXML private TextField tf_remove_username;

    // Table view (view all managers)
    @FXML private TableView<Manager> tableView; // fx:id not present in FXML; keep for future enhancement
    @FXML private TableColumn<Manager, String> tc_id;
    @FXML private TableColumn<Manager, String> tc_username;
    @FXML private TableColumn<Manager, String> tc_name;
    @FXML private TableColumn<Manager, String> tc_lastname;

    @FXML
    private void addManager() {
        String username = tf_reg_username.getText();
        String pass = pf_reg_pass.getText();
        String passConf = pf_reg_passconf.getText();
        String email = tf_reg_email.getText();
        String name = tf_reg_name.getText();
        String last = tf_reg_lastname.getText();

        if (username.isEmpty() || pass.isEmpty() || passConf.isEmpty() || email.isEmpty() || name.isEmpty() || last.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "All fields are required");
            return;
        }
        if (!pass.equals(passConf)) {
            showAlert(Alert.AlertType.ERROR, "Passwords do not match");
            return;
        }
        try {
            // Check username uniqueness in database
            if (ManagerDAO.usernameExists(username)) {
                showAlert(Alert.AlertType.ERROR, "Username already exists");
                return;
            }
            
            Manager m = new Manager(name, Integer.toString(ZOO.ID++), last, username, ZOO.hashIt(pass), email, "---");
            
            // Add to database
            ManagerDAO.addManager(m);
            
            // Add to ArrayList for backward compatibility
            managersArrayList.add(m);
            
            showAlert(Alert.AlertType.INFORMATION, "Manager added successfully");
            clearAddFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Failed to add manager: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void removeManager() {
        String username = tf_remove_username.getText();
        if (username.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Enter a username to remove");
            return;
        }
        try {
            // Remove from database
            boolean removed = ManagerDAO.removeManager(username);
            
            if (removed) {
                // Remove from ArrayList for backward compatibility
                managersArrayList.removeIf(m -> m.getUsername().equals(username));
                showAlert(Alert.AlertType.INFORMATION, "Manager removed");
            } else {
                showAlert(Alert.AlertType.WARNING, "Manager not found");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Failed to remove manager: " + e.getMessage());
            e.printStackTrace();
        }
        tf_remove_username.clear();
    }

    @FXML
    private void backToSuperAdmin() {
        try {
            Stage stage = (Stage) tf_reg_username.getScene().getWindow();
            stage.close();
            Stage superAdminStage = new Stage();
            FXMLLoader loader2 = new FXMLLoader();
            Pane root = (Pane) loader2.load(Paths.get("src/resources/superadmin.fxml").toUri().toURL());
            SuperAdminController superAdmin = (SuperAdminController) loader2.getController();
            superAdminStage.setTitle("SuperAdmin Page");
            superAdminStage.setScene(new Scene(root, 1000, 500));
            superAdminStage.setResizable(false);
            superAdminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearAddFields() {
        tf_reg_username.clear();
        pf_reg_pass.clear();
        pf_reg_passconf.clear();
        tf_reg_email.clear();
        tf_reg_name.clear();
        tf_reg_lastname.clear();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type, msg, ButtonType.OK);
        a.showAndWait();
    }
}

