package com.codecool.onlineshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    Connection connection;
    String databasePath;

    public Connector(String databasePath) {
        this.databasePath = databasePath;
        connection = connectToDatabase(databasePath);
    }

    private Connection connectToDatabase(String databasePath) {
        connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(databasePath);
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getDatabaseConnection() {
        return connection;
    }
}