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

    @Override
    public List<User> getUserData() {
        return users;
    }

    @Override
    public User getUser(int id) {
        return users.get(id);
    }

    @Override
    public void updateUser(int userId, String columnName, String newUpdate) {

        try {
            statement = connection.createStatement();
            String update = "UPDATE Users set " + columnName + "=" + "\"" + newUpdate + "\"" + "where UserID = "
                    + userId + ";";
            statement.executeUpdate(update);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {

        try {
            statement = connection.createStatement();
            String delete = "DELETE from Users WHERE UserID = " + userId + ";";
            statement.executeUpdate(delete);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String name, String password, String userType) {

        try {
            statement = connection.createStatement();
            String insertInto = "INSERT INTO Users (Name, Password, UserType)" + "VALUES (\"" + name + "\", \""
                    + password + "\", \"" + userType + "\");";
            statement.executeUpdate(insertInto);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}