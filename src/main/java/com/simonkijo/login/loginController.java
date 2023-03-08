package com.simonkijo.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginController {
    @FXML private Button btnCancel;@FXML private Button btnLogin;@FXML private Button btnRegister;
    @FXML private TextField passWord;@FXML private TextField userName;
    @FXML private Label smsStatus;
    private String uName,pWord;

    @FXML
    void btnCancelAcction(ActionEvent event) {
        Stage stage =(Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnLoginAction(ActionEvent event) {

        if(!userName.getText().isBlank() && !passWord.getText().isBlank()){
            //smsStatus.setText("thanks for login");
            loginValidate();
        }else{
            smsStatus.setText(" Please enter \n username and password");
        }
    }

    @FXML
    void btnRegisterAction(ActionEvent event) {
        try {
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 661, 501);
            registerStage.initStyle(StageStyle.DECORATED);
            registerStage.setScene(scene);
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void loginValidate(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection c = connectNow.getConnection();

        uName = userName.getText();
        pWord = passWord.getText();
        String query = "SELECT `userName` FROM `login` WHERE `userName`='"+uName+"' AND `passWord`='"+pWord+"'";

        try{

            if(connectNow.dbConnectError=="connected"){
                //System.out.println("kijo connected : "+connectNow.dbConnectError);
                Statement statement = c.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                if(resultSet.next()){
                    smsStatus.setText(resultSet.getString("userName"));
                    System.out.println(" ROW: " + resultSet.getString("userName") + "\n USER: " + uName + "\n PASS: " + pWord);
                    //opens personSearch stage
                    try {
                        Stage personSearchStage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("personSearch.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
                        personSearchStage.initStyle(StageStyle.DECORATED);
                        personSearchStage.setScene(scene);
                        personSearchStage.show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    smsStatus.setText(" Invalid \nusername or password \nNew User !!!!! please register.\n(By clicking 'Register')");
                }
            }else{
                //System.out.println("kijo disconnected : "+connectNow.dbConnectError);
                smsStatus.setText("MySQL Server is offline,\nPlease turn on");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}