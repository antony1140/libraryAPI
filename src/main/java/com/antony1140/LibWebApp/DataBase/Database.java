package com.antony1140.LibWebApp.DataBase;
import java.sql.*;

public class Database {

    private static String url = "jdbc:mysql://localhost:3306/library_systemDB";
    private static String username = "root";
    private static String password = "";

    public Database(){

    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("Connected to Database");
        }
        connection = DriverManager.getConnection(url, username, password);
        if (connection != null){
            System.out.println("Connected to Database");
        }
        else{
            System.out.println("Unable to Connect to Database");
        }
        return connection;
    }

}
