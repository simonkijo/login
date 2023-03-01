package com.simonkijo.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class registerController {
    @FXML private Label registerSuccess; @FXML private Label passWordMatch;
    @FXML private Button btnClose_; @FXML private Button btnRegister_;
    @FXML
    private PasswordField cpword; @FXML private PasswordField pword;
    @FXML
    private TextField fname; @FXML private TextField lname; @FXML private TextField uname;
    private String fname_,lname_,uname_,pword_,cpword_;
    @FXML
    void btnCloseAction_(ActionEvent event) {
        Stage stage = (Stage)btnClose_.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnRegisterAction_(ActionEvent event) {
        validateRegistration();
    }
    public void validateRegistration(){
        fname_ = fname.getText();lname_=lname.getText();uname_=uname.getText();pword_=pword.getText();cpword_=cpword.getText();
        if(!fname_.isBlank()&&!lname_.isBlank()&&!uname_.isBlank()&&!pword_.isBlank()&&!cpword_.isBlank()){
            if(pword_.equals(cpword_)){
                //registerSuccess.setText("you entered some values!!!!");
                //System.out.println("sawa password: "+pword_+"\n sawa confirm password: "+cpword_);
                registerUser(fname_,lname_,uname_,pword_);
            }else{
                passWordMatch.setText("Password does not match ");
                //System.out.println("c password: "+pword_+"\n c confirm password: "+cpword_);
            }
        }else{
            registerSuccess.setText("Please all fields must be filled.");
        }
    }
    public void registerUser(String f,String l,String u,String p){
        DatabaseConnection connectDB = new DatabaseConnection();
        Connection c = connectDB.getConnection();
        String query = "INSERT INTO `login`(`id`,`firstName`,`lastName`,`userName`,`passWord`) VALUES(NULL,'"+f+"','"+l+"','"+u+"','"+p+"')";
        try{
            if(connectDB.dbConnectError=="connected") {
                Statement statement = c.createStatement();
                statement.executeUpdate(query);
                registerSuccess.setText("User Registered Successfully");
            }else{
                registerSuccess.setText("MySQL Server is offline,\nPlease turn on");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
