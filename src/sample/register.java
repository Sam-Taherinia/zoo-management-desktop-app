package sample;


import Existings.Visitor;
import Existings.ZOO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

import static Existings.ZOO.visitorsArrayList; //Important
import static Existings.ZOO.ID; //Important
import static sample.Main.writeVisitors;


public class register implements Initializable {

    //buttons here
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
    private Label lbl_status ;

    @FXML
    private Label lbl_time;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private AnchorPane anchor2;

    public static void backToMainSection() {

        try {

            Stage registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(Paths.get("src/sample/sample.fxml").toUri().toURL());

            register register = (register) loader.getController();
            registerStage.setTitle("MeysamTN ZOO !");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();


        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    @FXML
    public void backToMainForm(javafx.event.ActionEvent event) {

        try {

            Stage stage = (Stage) this.btn_back.getScene().getWindow();
            stage.close();
            backToMainSection();


        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    @FXML
    public void AddVisitor(javafx.event.ActionEvent event) throws IOException {


        String tempusername = tf_reg_username.getText().toString();
        String temppass = pf_reg_pass.getText().toString();
        String temppassconf = pf_reg_passconf.getText().toString();
        String tempemail = tf_reg_email.getText().toString();
        String tempname = tf_reg_name.getText().toString();
        String templastname = tf_reg_lastname.getText().toString();

        if (temppass.equals(temppassconf)) {

            Visitor V = new Visitor(tempname, Integer.toString(ID++), templastname, tempusername, ZOO.HashIt(temppass), tempemail, "---", "0");
            visitorsArrayList.add(V);

            writeVisitors();

            lbl_status.setText("User was added successfully");
            lbl_status.setTextFill(Paint.valueOf("#4CAF50"));

            //JOptionPane.showMessageDialog(null , "User "+tempname+" was added successfully");


            Stage stage = (Stage) this.btn_registerconf.getScene().getWindow();
            stage.close();
            backToMainSection();
            return;

        }else {

            JOptionPane.showMessageDialog(null , "Confirm your password again !!!");


        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

//    @FXML
//    private void loadMain (javafx.event.ActionEvent event) throws IOException {
//
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("sample.fxml")) ;
//        anchor1.getChildren().setAll(anchorPane) ;
//
//
//    }
}
