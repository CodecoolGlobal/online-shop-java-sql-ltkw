package com.codecool.onlineshop.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.codecool.onlineshop.dao.Connector;
import com.codecool.onlineshop.model.User;

public class UsersDaoImpl implements UsersDao {
    Connector connector;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    User user;
    private List<User> users;

    public UsersDaoImpl() {
        connector = new Connector("src/main/resources/databases/OnlineShop.db");
        connection = connector.getDatabaseConnection();
        users = new ArrayList<>();
        addUserData();
    }

    public List<User> getUserData() {
        return users;
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }

    private List<User> addUserData() {

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users;");
            while (resultSet.next()) {
                int id = resultSet.getInt("UserID");
                String name = resultSet.getString("Name");
                String password = resultSet.getString("Password");
                String userType = resultSet.getString("UserType");
                user = new User(id, name, password, userType);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}