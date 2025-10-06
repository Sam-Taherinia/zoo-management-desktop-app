package com.zoo.app.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.zoo.app.utils.ZOO.superadmin;

public class SuperAdminCheckMoneyController implements Initializable {

    @FXML private Label lbl_money;
    @FXML private Label lbl_time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTime(lbl_time);
        updateMoneyDisplay();
    }

    @FXML
    private void updateMoney() {
        // Refresh the money display from the current superadmin data
        updateMoneyDisplay();
        showAlert(Alert.AlertType.INFORMATION, "Money information updated");
    }

    @FXML
    private void backToSuperAdmin() {
        try {
            Stage stage = (Stage) lbl_money.getScene().getWindow();
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

    private void updateMoneyDisplay() {
        // Display the SuperAdmin's money (which represents the zoo's bank account)
        lbl_money.setText(superadmin.getMoney());
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
