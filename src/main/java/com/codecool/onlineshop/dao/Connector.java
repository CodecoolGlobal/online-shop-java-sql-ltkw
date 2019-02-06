package com.codecool.onlineshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector {
    Connection connection;
    String database;

    public Connector(String database) {
        this.database = database;
        connection = connectToDatabase(database);
    }

    private Connection connectToDatabase(String database) {
        connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(database);
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