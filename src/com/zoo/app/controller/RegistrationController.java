package com.zoo.app.controller;

import com.zoo.app.dao.VisitorDAO;
import com.zoo.app.model.people.Visitor;
import com.zoo.app.utils.ZOO;
import com.zoo.app.view.ZooApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.zoo.app.utils.ZOO.visitorsArrayList;
import static com.zoo.app.utils.ZOO.ID;

public class RegistrationController implements Initializable {

    @FXML
    private TextField tf_reg_username;
    @FXML
    private TextField tf_reg_email;
    @FXML
    private TextField tf_reg_name;
    @FXML
    private TextField tf_reg_lastname;

    @FXML
    private PasswordField pf_reg_pass;
    @FXML
    private PasswordField pf_reg_passconf;

    @FXML
    private Button btn_registerconf;
    @FXML
    private Button btn_back;

    @FXML
    private Label lbl_status;

    @FXML
    private Label lbl_time;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private AnchorPane anchor2;

    @FXML
    public void backToMainForm(javafx.event.ActionEvent event) {
        try {
            Stage stage = (Stage) this.btn_back.getScene().getWindow();
            stage.close();
            ManagerController.backToMainSection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void addVisitor(javafx.event.ActionEvent event) throws IOException {
        String tempusername = tf_reg_username.getText().toString();
        String temppass = pf_reg_pass.getText().toString();
        String temppassconf = pf_reg_passconf.getText().toString();
        String tempemail = tf_reg_email.getText().toString();
        String tempname = tf_reg_name.getText().toString();
        String templastname = tf_reg_lastname.getText().toString();

        if (temppass.equals(temppassconf)) {
            try {
                // Check if username already exists
                if (VisitorDAO.usernameExists(tempusername)) {
                    lbl_status.setText("Username already exists");
                    lbl_status.setTextFill(Paint.valueOf("#F44336"));
                    return;
                }

                Visitor V = new Visitor(tempname, Integer.toString(ID++), templastname, tempusername, ZOO.hashIt(temppass), tempemail, "---", "0");
                
                // Add to database
                VisitorDAO.addVisitor(V);
                
                // Add to ArrayList for backward compatibility
                visitorsArrayList.add(V);

                lbl_status.setText("User was added successfully");
                lbl_status.setTextFill(Paint.valueOf("#4CAF50"));

                Stage stage = (Stage) this.btn_registerconf.getScene().getWindow();
                stage.close();
                ManagerController.backToMainSection();
                return;
            } catch (Exception e) {
                lbl_status.setText("Error creating user: " + e.getMessage());
                lbl_status.setTextFill(Paint.valueOf("#F44336"));
                e.printStackTrace();
                return;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Confirm your password again !!!");
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
