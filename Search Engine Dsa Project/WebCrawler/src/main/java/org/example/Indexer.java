package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    static Connection connection=null;
    Indexer(Document document, String url){

//        Select important element of the document
    try {
        String title = document.title();
        String link = url;
        String text = document.text();
        connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into pages value(?,?,?);");
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, url);
        preparedStatement.setString(3, text);
        preparedStatement.executeUpdate();
    }
    catch(SQLException sqlException){
        sqlException.printStackTrace();
    }

    }

}
