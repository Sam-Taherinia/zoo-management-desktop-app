package sample;

import Existings.Manager;
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
import Existings.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import static Existings.ZOO.superadmin;

import static Existings.ZOO.ID;
import static Existings.ZOO.managersArrayList;
import static Existings.ZOO.visitorsArrayList;
import static sample.Main.writeManagers;
import static sample.Main.writeVisitors;


public class superadmin implements Initializable {

    @FXML
    private Button btn_change_info;
    @FXML
    private Button btn_manage_managers;
    @FXML
    private Button btn_manage_money;
    @FXML
    private Button btn_log_out;
    @FXML
    private Label lbl_time;

//    @FXML
//    private Button btn_save_info;


    @FXML
    private AnchorPane anchorChap;

    //-----------------------------

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
    private Button btn_add_manager;

    //-----------------------------------

    @FXML
    private TextField tf_remove_username;

    @FXML
    private Button btn_remove_manager;

    //----------------------------------

    @FXML
    private Label lbl_money;

    //---------------------------------

    @FXML
    private TextField tf_email ;

    @FXML
    private TextField tf_name ;

    @FXML
    private TextField tf_lastname ;

    @FXML
    private Button btn_save_info ;

//    public static void backToMainSection(){
//
//        try {
//
//            Stage registerStage = new Stage() ;
//            FXMLLoader loader = new FXMLLoader() ;
//            Pane root = (Pane) loader.load(Paths.get("src/sample/sample.fxml").toUri().toURL());
//
//            register register = (register) loader.getController();
//            registerStage.setTitle("Zoo Management App");
//            registerStage.setScene(new Scene(root, 1000, 500));
//            registerStage.setResizable(false);
//            registerStage.show();
//
//
//        }catch (IOException e){
//
//            e.printStackTrace();
//
//        }
//
//    }

    @FXML
    public void backToMainForm(javafx.event.ActionEvent event) {

        try {

            Stage stage = (Stage) this.btn_log_out.getScene().getWindow();
            stage.close();
            forgetpass.backToMainSection();


        } catch (Exception ex) {

            ex.printStackTrace();

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

    @FXML
    private void loadchap(javafx.event.ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("superadmin.fxml"));
        anchorChap.getChildren().setAll(anchorPane);


    }

    @FXML
    private void loadChangeInfo(javafx.event.ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("SuperAdminChangeInfo.fxml"));
        anchorChap.getChildren().setAll(anchorPane);


    }

    @FXML
    private void loadManageM(javafx.event.ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("SuperAdminManageM.fxml"));
        anchorChap.getChildren().setAll(anchorPane);


    }

    @FXML
    private void loadMoney(javafx.event.ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("SuperAdminCheckMoney.fxml"));
        anchorChap.getChildren().setAll(anchorPane);


    }

    @FXML
    public void AddManager(javafx.event.ActionEvent event) throws IOException {


        String tempusername = tf_reg_username.getText().toString();
        String temppass = pf_reg_pass.getText().toString();
        String temppassconf = pf_reg_passconf.getText().toString();
        String tempemail = tf_reg_email.getText().toString();
        String tempname = tf_reg_name.getText().toString();
        String templastname = tf_reg_lastname.getText().toString();

        if (temppass.equals(temppassconf)) {

            Manager m = new Manager(tempname, Integer.toString(ID++), templastname, tempusername, ZOO.HashIt(temppass), tempemail, "---");
            managersArrayList.add(m);

            writeManagers();

//            lbl_status.setText("User was added successfully");
//            lbl_status.setTextFill(Paint.valueOf("#4CAF50"));

            JOptionPane.showMessageDialog(null, "Manager " + tempname + " was added successfully");


            Stage stage = (Stage) this.btn_add_manager.getScene().getWindow();
            stage.close();
            backToMainSection();
            return;

        } else {

            JOptionPane.showMessageDialog(null, "Confirm your password again !!!");


        }


    }

    public static void backToMainSection() {

        try {

            Stage registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(Paths.get("src/sample/superadmin.fxml").toUri().toURL());

            register register = (register) loader.getController();
            registerStage.setTitle("SuperAdmin Page");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();


        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void RemoveManager(javafx.event.ActionEvent event) throws IOException {

        boolean flag = true;

        String tempusername = tf_remove_username.getText().toString();

        for (int i = 0; i < managersArrayList.size(); i++) {

            if (tempusername.equals(managersArrayList.get(i).getUsername())) {

                managersArrayList.remove(i);

                writeManagers();

                JOptionPane.showMessageDialog(null, "Manager have been removed from the zoo");

                flag = false;

                return;

            }

        }

        if (flag) {

            JOptionPane.showMessageDialog(null, "not such a manager with this username!!!");

        }

    }

    @FXML
    public void UpdateMoney(javafx.event.ActionEvent event) throws IOException {

        FileReader fr = new FileReader("src/IO files/Superadmin.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");

            lbl_money.setText(str[7]);


        }

    }

    @FXML
    public void SaveNewInfo (javafx.event.ActionEvent event){

        String tempemail = tf_email.getText().toString();

        String tempname = tf_name.getText().toString();

        String templastname = tf_lastname.getText().toString();

        //TODO add password and pass conf, here !


        if(tf_name != null){

            superadmin.setName(tempname);

        }

        if(tf_email != null) {

            superadmin.setEmailAddress(tempemail);
        }

        if(tf_lastname != null) {

            superadmin.setLastName(templastname);

        }




        }

}
