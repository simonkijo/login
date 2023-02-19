package com.simonkijo.login;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection connection;
    public String dbConnectError;
    public Connection getConnection(){
        String dbName="pluto";
        String dbUser="root";
        String dbPassword="";
        String url="jdbc:mysql://localhost/"+dbName;

        try{
            dbConnectError="connected";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,dbUser,dbPassword);
        }catch (Exception e){
            //e.printStackTrace();
            //System.out.println("no connection to database\n"+e.getMessage());
            dbConnectError=e.getMessage();
        }
        return connection;
    }
}
