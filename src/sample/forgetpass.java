package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import sample.Controller.* ;

public class forgetpass implements Initializable {

    @FXML
    private TextField tf_username ;
    @FXML
    private TextField tf_name ;
    @FXML
    private TextField tf_lastname ;
    @FXML
    private TextField tf_email ;

    @FXML
    private PasswordField pf_pass ;
    @FXML
    private PasswordField pf_pass_conf ;

    @FXML
    private Button btn_changepass_conf ;
    @FXML
    private Button btn_back;

    @FXML
    private Label lbl_time ;


    public static void backToMainSection(){

        try {

            Stage registerStage = new Stage() ;
            FXMLLoader loader = new FXMLLoader() ;
            Pane root = (Pane) loader.load(Paths.get("src/sample/sample.fxml").toUri().toURL());

            register register = (register) loader.getController();
            registerStage.setTitle("Zoo Management App");
            registerStage.setScene(new Scene(root, 1000, 500));
            registerStage.setResizable(false);
            registerStage.show();


        }catch (IOException e){

            e.printStackTrace();

        }

    }

    @FXML
    public void backToMainForm(javafx.event.ActionEvent event){

        try {

            Stage stage = (Stage) this.btn_back.getScene().getWindow();
            stage.close();
            backToMainSection();


        }catch (Exception ex){

            ex.printStackTrace();

        }

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ShowTime(lbl_time);


    }

    private void ShowTime(Label lbl_time) {

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
