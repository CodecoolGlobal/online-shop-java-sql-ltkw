package com.codecool.onlineshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.codecool.onlineshop.dao.Connector;

public class UsersDao {

    Connector connector = new Connector("jdbc:sqlite:Users.db");
    Connection connection = connector.getDatabaseConnection();
}