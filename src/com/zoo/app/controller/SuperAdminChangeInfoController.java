package com.zoo.app.controller;

import com.zoo.app.dao.SuperAdminDAO;
import com.zoo.app.utils.ZOO;
import com.zoo.app.view.ZooApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.zoo.app.utils.ZOO.superadmin;

public class SuperAdminChangeInfoController implements Initializable {

    @FXML private PasswordField pf_last_pass;
    @FXML private PasswordField pf_new_pass;
    @FXML private PasswordField pf_new_passconf;
    @FXML private TextField tf_email;
    @FXML private TextField tf_name;
    @FXML private TextField tf_lastname;
    @FXML private Label lbl_time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTime(lbl_time);
        // Pre-populate fields with current superadmin info
        tf_email.setText(superadmin.getEmailAddress());
        tf_name.setText(superadmin.getName());
        tf_lastname.setText(superadmin.getLastName());
    }

    @FXML
    private void saveInformation() {
        String lastPass = pf_last_pass.getText();
        String newPass = pf_new_pass.getText();
        String newPassConf = pf_new_passconf.getText();
        String email = tf_email.getText();
        String name = tf_name.getText();
        String lastname = tf_lastname.getText();

        // Validate last password
        if (!ZOO.hashIt(lastPass).equals(superadmin.getPassword())) {
            showAlert(Alert.AlertType.ERROR, "Current password is incorrect");
            return;
        }

        // If new password is provided, validate confirmation
        if (!newPass.isEmpty()) {
            if (!newPass.equals(newPassConf)) {
                showAlert(Alert.AlertType.ERROR, "New passwords do not match");
                return;
            }
            superadmin.setPassword(ZOO.hashIt(newPass));
        }

        // Update other fields
        superadmin.setEmailAddress(email);
        superadmin.setName(name);
        superadmin.setLastName(lastname);

        try {
            // Save to database
            SuperAdminDAO.saveSuperAdmin(superadmin);
            showAlert(Alert.AlertType.INFORMATION, "Information updated successfully");
            backToSuperAdmin();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Failed to save information: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void backToSuperAdmin() {
        try {
            Stage stage = (Stage) tf_name.getScene().getWindow();
            stage.close();
            
            Stage superAdminStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(Paths.get("src/resources/superadmin.fxml").toUri().toURL());
            
            SuperAdminController superAdmin = (SuperAdminController) loader.getController();
            superAdminStage.setTitle("SuperAdmin Page");
            superAdminStage.setScene(new Scene(root, 1000, 500));
            superAdminStage.setResizable(false);
            superAdminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type, msg, ButtonType.OK);
        a.showAndWait();
    }

    private void showTime(Label lbl_time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lbl_time.setText(dtf.format(now));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }
        }).start();
    }
}
