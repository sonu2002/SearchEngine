package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static Connection connection=null;
    public static Connection getConnection() {
        if(connection!=null){
            return connection;
        }
        String user="root";
        String password="Psycho@132103";
        String db="searchengineapp";

        return getConnection(user, password, db);
    }

    private static Connection getConnection(String user, String password, String db){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + user + "&password=" + password);
        }
        catch(SQLException | ClassNotFoundException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }

}
