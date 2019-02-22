package com.codecool.onlineshop.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.codecool.onlineshop.model.User;

public class UsersDaoImpl implements UsersDao {

    private Connection connection;
    private List<User> users;

    public UsersDaoImpl() {
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
        connection = initializeConnection();
        PreparedStatement updateUser;
        String updateString = "UPDATE Users SET " + columnName + " = ? WHERE UserID = ?";
        try {
            updateUser = connection.prepareStatement(updateString);
            updateUser.setString(1, newUpdate);
            updateUser.setInt(2, userId);
            updateUser.executeUpdate();
            updateUser.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        connection = initializeConnection();
        PreparedStatement deleteUser;
        String deleteString = "DELETE FROM Users WHERE UserID = ?";
        try {
            deleteUser = connection.prepareStatement(deleteString);
            deleteUser.setInt(1, userId);
            deleteUser.executeUpdate();
            deleteUser.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String userName, String userPassword, String userType) {
        connection = initializeConnection();
        PreparedStatement addUser;
        String addString = "INSERT INTO Users (Name, Password, UserType) VALUES (?, ?, ?)";
        try {
            addUser = connection.prepareStatement(addString);
            addUser.setString(1, userName);
            addUser.setString(2, userPassword);
            addUser.setString(3, userType);
            addUser.executeUpdate();
            addUser.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isValid(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return true;
            }
        }
        return false;
    }

    private Connection initializeConnection() {
        final String DATABASEPATH = "src/main/resources/databases/OnlineShop.db";
        Connector connector = new Connector(DATABASEPATH);
        return connector.getDatabaseConnection();
    }

    private void addUserData() {
        connection = initializeConnection();
        User user;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users;");
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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}