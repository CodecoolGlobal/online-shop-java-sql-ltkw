package com.codecool.onlineshop.service;

import com.codecool.onlineshop.dao.UsersDaoImpl;
import com.codecool.onlineshop.view.View;

public class LoginService {

    private UsersDaoImpl usersDao;
    private View view;

    public LoginService() {
        this.usersDao = new UsersDaoImpl();
        this.view = new View();
    }

    public void handleLogin() {
        
    }
}