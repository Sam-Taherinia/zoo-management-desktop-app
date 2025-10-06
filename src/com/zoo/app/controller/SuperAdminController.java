package com.zoo.app.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SuperAdminController implements Initializable {

    @FXML
    private Button btn_log_out;

    @FXML
    private Label lbl_time;

    @FXML
    public void backToMainForm(javafx.event.ActionEvent event) {
        try {
            Stage stage = (Stage) this.btn_log_out.getScene().getWindow();
            stage.close();
            ManagerController.backToMainSection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTime();
    }

    @FXML
    public void loadChangeInfo() {
        try {
            Stage currentStage = (Stage) this.btn_log_out.getScene().getWindow();
            currentStage.close();
            
            Stage changeInfoStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(Paths.get("src/resources/SuperAdminChangeInfo.fxml").toUri().toURL());
            
            SuperAdminChangeInfoController changeInfo = (SuperAdminChangeInfoController) loader.getController();
            changeInfoStage.setTitle("Change Information");
            changeInfoStage.setScene(new Scene(root, 1000, 500));
            changeInfoStage.setResizable(false);
            changeInfoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadManageManagers() {
        try {
            Stage currentStage = (Stage) this.btn_log_out.getScene().getWindow();
            currentStage.close();
            
            Stage manageStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(Paths.get("src/resources/SuperAdminManageM.fxml").toUri().toURL());
            
            SuperAdminManageManagersController manageManagers = (SuperAdminManageManagersController) loader.getController();
            manageStage.setTitle("Manage Managers");
            manageStage.setScene(new Scene(root, 1000, 500));
            manageStage.setResizable(false);
            manageStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadCheckMoney() {
        try {
            Stage currentStage = (Stage) this.btn_log_out.getScene().getWindow();
            currentStage.close();
            
            Stage checkMoneyStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(Paths.get("src/resources/SuperAdminCheckMoney.fxml").toUri().toURL());
            
            SuperAdminCheckMoneyController checkMoney = (SuperAdminCheckMoneyController) loader.getController();
            checkMoneyStage.setTitle("Check Money");
            checkMoneyStage.setScene(new Scene(root, 1000, 500));
            checkMoneyStage.setResizable(false);
            checkMoneyStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showTime() {
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
