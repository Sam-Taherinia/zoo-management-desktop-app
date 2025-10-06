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

public class ManagerController implements Initializable {

    @FXML
    private Button btn_log_out;

    @FXML
    private Label lbl_time;

    @FXML
    public void backToMainForm(javafx.event.ActionEvent event) {
        try {
            Stage stage = (Stage) this.btn_log_out.getScene().getWindow();
            stage.close();
            backToMainSection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void backToMainSection() {
        try {
            Stage mainStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(Paths.get("src/resources/sample.fxml").toUri().toURL());

            MainController main = (MainController) loader.getController();
            mainStage.setTitle("MeysamTN ZOO !");
            mainStage.setScene(new Scene(root, 1000, 500));
            mainStage.setResizable(false);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTime();
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
