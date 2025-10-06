package sample;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static Existings.ZOO.managersArrayList;
import static Existings.ZOO.visitorsArrayList;
import static Existings.ZOO.zookeepersArrayList;
import static Existings.ZOO.superadmin ;

public class Controller implements Initializable{

//    LoginAuth loginAuth = new LoginAuth() ;

    @FXML
    private TextField tf_username ;

    @FXML
    private PasswordField pf_password ;

    @FXML
    private Button btn_login ;

    @FXML
    private Button btn_register ;

    @FXML
    private Button btn_forgot ;

    @FXML
    private ImageView iv_status ;

    @FXML
    private Label lbl_status ;

    @FXML
    private Label lbl_time;

    @FXML
    private AnchorPane mainanchor ;

    @FXML
    private AnchorPane anchor2 ;

    @FXML
    private AnchorPane anchor1 ;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {

//        if (true){
//
//            File f = new File("src/images/green.png");
//            Image i = new Image(f.toURI().toString());
//            iv_status.setImage(i);
//            lbl_status.setText("Database loaded successfully");
//            lbl_status.setTextFill(Paint.valueOf("#4CAF50"));
//
//        }else {
//
//            File f = new File("src/images/red.png");
//            Image i = new Image(f.toURI().toString());
//            iv_status.setImage(i);
//            lbl_status.setText("Database can't be loaded !");
//            lbl_status.setTextFill(Paint.valueOf("#F44336"));
//
//        }






    public void managementSection(){

        try {

            Stage managerStage = new Stage() ;
            FXMLLoader loader = new FXMLLoader() ;
            //Pane root = (Pane) loader.load(getClass().getResource("/sample/visitor.fxml").openStream());
            Pane root = (Pane) loader.load(Paths.get("src/sample/manager.fxml").toUri().toURL());

            manager manager = (manager) loader.getController();
            //Scene scene = new Scene(root) ;
            //managerStage.setScene(scene);
            managerStage.setTitle("Visitors Page");
            managerStage.setScene(new Scene(root, 1000, 500));
            managerStage.setResizable(false);
            managerStage.show();


        }catch (IOException e){

            e.printStackTrace();

        }

    }

    public void registrSection(){

        try {

            Stage registerStage = new Stage() ;
            FXMLLoader loader1 = new FXMLLoader() ;
            Pane root = (Pane) loader1.load(Paths.get("src/sample/registration.fxml").toUri().toURL());

            register register = (register) loader1.getController();
            registerStage.setTitle("Registration Page");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();


        }catch (IOException e){

            e.printStackTrace();

        }

    }

    public void forgetPassSection(){

        try {

            Stage registerStage = new Stage() ;
            FXMLLoader loader2 = new FXMLLoader() ;
            Pane root = (Pane) loader2.load(Paths.get("src/sample/forgetpass.fxml").toUri().toURL());

            register register = (register) loader2.getController();
            registerStage.setTitle("Password recovery Page");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();

        }catch (IOException e){

            e.printStackTrace();

        }

    }

    public void VistorsSection (){

        try {

            Stage registerStage = new Stage() ;
            FXMLLoader loader2 = new FXMLLoader() ;
            Pane root = (Pane) loader2.load(Paths.get("src/sample/visitor.fxml").toUri().toURL());

            register register = (register) loader2.getController();
            registerStage.setTitle("Visitors Page");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();

        }catch (IOException e){

            e.printStackTrace();

        }


    }

    public void ZooKeepersSection (){

        try {

            Stage registerStage = new Stage() ;
            FXMLLoader loader2 = new FXMLLoader() ;
            Pane root = (Pane) loader2.load(Paths.get("src/sample/zookeeper.fxml").toUri().toURL());

            register register = (register) loader2.getController();
            registerStage.setTitle("ZooKeepers Page");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();

        }catch (IOException e){

            e.printStackTrace();

        }


    }

    public void SuperAdminSection (){

        try {

            Stage registerStage = new Stage() ;
            FXMLLoader loader2 = new FXMLLoader() ;
            Pane root = (Pane) loader2.load(Paths.get("src/sample/superadmin.fxml").toUri().toURL());

            register register = (register) loader2.getController();
            registerStage.setTitle("SuperAdmin Page");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();

        }catch (IOException e){

            e.printStackTrace();

        }


    }




//    @FXML
//    public void loginChecker(javafx.event.ActionEvent event){
//
//        try {
//
//            LoginAuth logCheck = new LoginAuth() ;
//
//            if (logCheck.checkUser(tf_username.getText().toString(), pf_password.getText().toString())){
//
//                Stage stage = (Stage) this.btn_login.getScene().getWindow();
//                stage.close();
//                managementSection();
//
//
//            }else {
//
//                JOptionPane.showMessageDialog(null , "Wrong Username or Password ... ");
//
//            }
//
//
//        }catch (Exception ex){
//
//            ex.printStackTrace();
//
//        }
//
//    }

    @FXML
    public void loginChecker(javafx.event.ActionEvent event){



        for (int i = 0 ; i < visitorsArrayList.size() ; i++){

            if (tf_username.getText().toString().equals(visitorsArrayList.get(i).getUsername()) && ZOO.HashIt(pf_password.getText().toString()).equals(visitorsArrayList.get(i).getPassword())){

                Stage stage = (Stage) this.btn_login.getScene().getWindow();
                stage.close();
                VistorsSection();//TODO change to load zoo keeper
                return;

            }

        }

        for (int i = 0 ; i < zookeepersArrayList.size() ; i++){

            if (tf_username.getText().toString().equals(zookeepersArrayList.get(i).getUsername()) && ZOO.HashIt(pf_password.getText().toString()).equals(zookeepersArrayList.get(i).getPassword())){

                Stage stage = (Stage) this.btn_login.getScene().getWindow();
                stage.close();
                ZooKeepersSection();//TODO change to load zookeeper
                return;

            }


        }

        for (int i = 0 ; i < managersArrayList.size() ; i++){

            if (tf_username.getText().toString().equals(managersArrayList.get(i).getUsername()) && ZOO.HashIt(pf_password.getText().toString()).equals(managersArrayList.get(i).getPassword())){

                Stage stage = (Stage) this.btn_login.getScene().getWindow();
                stage.close();
                managementSection(); //TODO change to load manager page
                return ;

            }


        }

        if (tf_username.getText().toString().equals(superadmin.getUsername()) && HashIt(pf_password.getText().toString()).equals(superadmin.getPassword())){

            Stage stage = (Stage) this.btn_login.getScene().getWindow();
            stage.close();
            SuperAdminSection(); //TODO change to load admin page
            return;

        }

        else {

            File f = new File("src/images/red.png");
            Image i = new Image(f.toURI().toString());
            iv_status.setImage(i);
            lbl_status.setText("Wrong username or password");
            lbl_status.setTextFill(Paint.valueOf("#F44336"));

            //JOptionPane.showMessageDialog(null , "Wrong Username or Password ... ");

        }

    }


    @FXML
    public void registerForm(javafx.event.ActionEvent event){

        try {

                Stage stage = (Stage) this.btn_register.getScene().getWindow();
                stage.close();
                registrSection();//TODO change to load register


        }catch (Exception ex){

            ex.printStackTrace();

        }

    }

    @FXML
    public void PassChangeForm(javafx.event.ActionEvent event){

        try {

            Stage stage = (Stage) this.btn_forgot.getScene().getWindow();
            stage.close();
            forgetPassSection(); //TODO change to load forget pass


        }catch (Exception ex){

            ex.printStackTrace();

        }

    }

    public static String HashIt(String Password) {

        return Integer.toString(Password.hashCode());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ShowTime(lbl_time);

    }

//    @FXML
//    private void loadSuperAdmin(ActionEvent event) throws IOException {
//
//        AnchorPane a = FXMLLoader.load(getClass().getResource("superadmin.fxml"));
//        mainanchor.getChildren().setAll(a) ;
//
//    }

    public void ShowTime(Label lbl_time){

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
//    private void loadrast (javafx.event.ActionEvent event) throws IOException {
//
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("rast.fxml")) ;
//        anchor1.getChildren().setAll(anchorPane) ;
//
//
//    }
//
//    @FXML
//    private void loadChangeInfo (javafx.event.ActionEvent event) throws IOException {
//
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("chap.fxml")) ;
//        anchor2.getChildren().setAll(anchorPane) ;
//
//
//    }


}
