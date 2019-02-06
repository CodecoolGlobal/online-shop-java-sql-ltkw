package com.codecool.onlineshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Connector {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String database;

    public Connector(String database) {
        this.database = "jdbc:sqlite:" + database;
        connection = connectToDatabase(database);
        statement = createStatement();
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

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet(String tableName) {
        resultSet = null;

        try {
            resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private Statement createStatement() {
        statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}