package com.simonkijo.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class personSearchController implements Initializable {
    @FXML
    private TableColumn<personSearchModel, String> DESC_tableColumn;
    @FXML
    private TableColumn<personSearchModel, String> FN_tableColumn;
    @FXML
    private TableColumn<personSearchModel, String> LN_tableColumn;
    @FXML
    private TableColumn<personSearchModel, String> PW_tableColumn;
    @FXML
    private TableColumn<personSearchModel, String> UN_tableColumn;
    @FXML
    private TextField searchPersonET;
    @FXML private TableView<personSearchModel> personSearchTableView;
    String fname,lname,uname,pword,desc;
    ObservableList<personSearchModel> personSearchModelObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DatabaseConnection dbConnect = new DatabaseConnection();
        Connection c = dbConnect.getConnection();
        //select query
        String searchQuery = "SELECT `firstName`,`lastName`,`userName`,`passWord`,`description` FROM `login`";

        try{
            Statement statement = c.createStatement();
            ResultSet queryOutPut = statement.executeQuery(searchQuery);

            while (queryOutPut.next()){
                 fname = queryOutPut.getString("firstName");
                 lname = queryOutPut.getString("lastName");
                 uname = queryOutPut.getString("userName");
                 pword = queryOutPut.getString("passWord");
                 desc = queryOutPut.getString("description");
                //populate observable list
                personSearchModelObservableList.add(new personSearchModel(fname,lname,uname,pword,desc));
            }
            //propertyValueFactory corresponds to new personSearchModel fields
            FN_tableColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            LN_tableColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            UN_tableColumn.setCellValueFactory(new PropertyValueFactory<>("user_name"));
            PW_tableColumn.setCellValueFactory(new PropertyValueFactory<>("pass_word"));
            DESC_tableColumn.setCellValueFactory(new PropertyValueFactory<>("description_"));

            personSearchTableView.setItems(personSearchModelObservableList);

            FilteredList<personSearchModel> filteredData = new FilteredList<>(personSearchModelObservableList,b->true);

            searchPersonET.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(personSearchModel -> {
                    //if no search value, keep current records
                    if(newValue.isEmpty()||newValue.isBlank()||newValue==null){
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();
                    if(personSearchModel.getFirst_name().toLowerCase().indexOf(searchKeyword) > -1){
                        return true; // match record found of first name
                    }else if(personSearchModel.getLast_name().toLowerCase().indexOf(searchKeyword) > -1){
                        return true; // match record found of last name
                    }else if(personSearchModel.getUser_name().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;// match record found of user name
                    }else if(personSearchModel.getPass_word().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;// match record found of password
                    }else if(personSearchModel.getDescription_().toLowerCase().indexOf(searchKeyword) > -1){
                        return true; // match record found of descriptions
                    }else{
                        return false;// no match found
                    }
                });
            });
            SortedList<personSearchModel> sortedData = new SortedList<>(filteredData);
            //bind sorted result with table view
            sortedData.comparatorProperty().bind(personSearchTableView.comparatorProperty());
            //apply filtered and sorted data to table view
            personSearchTableView.setItems(sortedData);

        }catch (SQLException e){
            Logger.getLogger(personSearchController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }
}
