package com.zoo.app.controller;

import com.zoo.app.dao.*;
import com.zoo.app.utils.ZOO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.zoo.app.utils.ZOO.managersArrayList;
import static com.zoo.app.utils.ZOO.visitorsArrayList;
import static com.zoo.app.utils.ZOO.zookeepersArrayList;
import static com.zoo.app.utils.ZOO.superadmin;

public class MainController implements Initializable {

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_register;

    @FXML
    private Button btn_forgot;

    @FXML
    private ImageView iv_status;

    @FXML
    private Label lbl_status;

    @FXML
    private Label lbl_time;

    @FXML
    private AnchorPane mainanchor;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private AnchorPane anchor1;

    public void managementSection() {
        try {
            Stage managerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(Paths.get("src/resources/manager.fxml").toUri().toURL());

            ManagerController manager = (ManagerController) loader.getController();
            managerStage.setTitle("Manager Page");
            managerStage.setScene(new Scene(root, 1000, 500));
            managerStage.setResizable(false);
            managerStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registrationSection() {
        try {
            Stage registerStage = new Stage();
            FXMLLoader loader1 = new FXMLLoader();
            Pane root = (Pane) loader1.load(Paths.get("src/resources/registration.fxml").toUri().toURL());

            RegistrationController register = (RegistrationController) loader1.getController();
            registerStage.setTitle("Registration Page");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forgetPasswordSection() {
        try {
            Stage forgetStage = new Stage();
            FXMLLoader loader2 = new FXMLLoader();
            Pane root = (Pane) loader2.load(Paths.get("src/resources/forgetpass.fxml").toUri().toURL());

            ForgetPasswordController forgetPass = (ForgetPasswordController) loader2.getController();
            forgetStage.setTitle("Password Recovery Page");
            forgetStage.setScene(new Scene(root, 1000, 500));
            forgetStage.setResizable(false);
            forgetStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visitorsSection() {
        try {
            Stage visitorStage = new Stage();
            FXMLLoader loader2 = new FXMLLoader();
            Pane root = (Pane) loader2.load(Paths.get("src/resources/visitor.fxml").toUri().toURL());

            VisitorController visitor = (VisitorController) loader2.getController();
            visitorStage.setTitle("Visitors Page");
            visitorStage.setScene(new Scene(root, 1000, 500));
            visitorStage.setResizable(false);
            visitorStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zooKeepersSection() {
        try {
            Stage zookeeperStage = new Stage();
            FXMLLoader loader2 = new FXMLLoader();
            Pane root = (Pane) loader2.load(Paths.get("src/resources/zookeeper.fxml").toUri().toURL());

            ZooKeeperController zookeeper = (ZooKeeperController) loader2.getController();
            zookeeperStage.setTitle("ZooKeepers Page");
            zookeeperStage.setScene(new Scene(root, 1000, 500));
            zookeeperStage.setResizable(false);
            zookeeperStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void superAdminSection() {
        try {
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

    @FXML
    public void loginChecker(javafx.event.ActionEvent event) {
        String username = tf_username.getText().trim();
        String hashedPassword = ZOO.hashIt(pf_password.getText());
        
        try {
            // Check visitors
            com.zoo.app.model.people.Visitor visitor = VisitorDAO.findByUsername(username);
            if (visitor != null && hashedPassword.equals(visitor.getPassword())) {
                Stage stage = (Stage) this.btn_login.getScene().getWindow();
                stage.close();
                visitorsSection();
                return;
            }

            // Check zookeepers
            com.zoo.app.model.people.ZooKeeper zookeeper = ZooKeeperDAO.findByUsername(username);
            if (zookeeper != null && hashedPassword.equals(zookeeper.getPassword())) {
                Stage stage = (Stage) this.btn_login.getScene().getWindow();
                stage.close();
                zooKeepersSection();
                return;
            }

            // Check managers
            com.zoo.app.model.people.Manager manager = ManagerDAO.findByUsername(username);
            if (manager != null && hashedPassword.equals(manager.getPassword())) {
                Stage stage = (Stage) this.btn_login.getScene().getWindow();
                stage.close();
                managementSection();
                return;
            }

            // Check superadmin
            com.zoo.app.model.people.SuperAdmin superAdmin = SuperAdminDAO.getSuperAdmin();
            if (superAdmin != null && username.equals(superAdmin.getUsername()) && 
                hashedPassword.equals(superAdmin.getPassword())) {
                Stage stage = (Stage) this.btn_login.getScene().getWindow();
                stage.close();
                superAdminSection();
                return;
            }

        } catch (Exception e) {
            System.err.println("Database error during login: " + e.getMessage());
            e.printStackTrace();
        }

        // Login failed
        File f = new File("src/images/red.png");
        Image i = new Image(f.toURI().toString());
        iv_status.setImage(i);
        lbl_status.setText("Wrong username or password");
        lbl_status.setTextFill(Paint.valueOf("#F44336"));
    }

    @FXML
    public void registerForm(javafx.event.ActionEvent event) {
        try {
            Stage stage = (Stage) this.btn_register.getScene().getWindow();
            stage.close();
            registrationSection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void passwordChangeForm(javafx.event.ActionEvent event) {
        try {
            Stage stage = (Stage) this.btn_forgot.getScene().getWindow();
            stage.close();
            forgetPasswordSection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTime(lbl_time);
    }

    public void showTime(Label lbl_time) {
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
