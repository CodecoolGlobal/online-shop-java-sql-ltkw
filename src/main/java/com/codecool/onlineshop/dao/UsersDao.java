package com.codecool.onlineshop.dao;

import java.util.List;

import com.codecool.onlineshop.model.User;

public interface UsersDao {

    public List<User> getUserData();
    public User getUser(int id);
    public void updateUser(int userId, String columnName, String newUpdate);
    public void deleteUser(int userId);
    public void addUser(String name, String password, String userType);
}