package com.codecool.onlineshop.dao;

import java.util.List;

import com.codecool.onlineshop.model.User;

public interface UsersDao {

    public List<User> getUserData();
    public User getUser(int id);
    public void updateUser(int userId, String columnName, String newUpdate);
    public void deleteUser(User user);
    public void addUser(User user);
}