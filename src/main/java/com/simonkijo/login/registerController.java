package com.simonkijo.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class registerController {
    @FXML
    private Label registerSuccess;
    @FXML
    private Button btnClose_;
    @FXML
    private Button btnRegister_;
    @FXML
    private PasswordField cpword;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private PasswordField pword;
    @FXML
    private TextField uname;
    @FXML
    void btnCloseAction_(ActionEvent event) {
        Stage stage = (Stage)btnClose_.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnRegisterAction_(ActionEvent event) {

    }

}
