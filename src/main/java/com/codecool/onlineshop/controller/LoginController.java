package com.codecool.onlineshop.controller;

import java.util.ArrayList;
import java.util.List;

import com.codecool.onlineshop.dao.UsersDaoImpl;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.View;

public class LoginController {

    private View view;
    boolean isRunning = true;
    private UsersDaoImpl usersDao;

    public LoginController() {
        this.usersDao = new UsersDaoImpl();
        this.view = new View();
        this.isRunning = true;
    }

    public void startLoginController() {
        handleLoginController();
    }

    public void handleLoginController() {
        while (isRunning) {
            view.clearScreen();
            view.displayMainMenu();
            int userInput = view.getIntegerInput();
            switch (userInput) {
                case 1:
                chooseController(handleLogin());;
                    break;
                case 2:
                    break;
                case 3:
                    isRunning = false;
                default:
                    break;
            }
        }
    }

    public User handleLogin() {
        List<User> users = usersDao.getUserData();
        view.showMessage("Name: ");
        String name = view.getStringInput();
        view.showMessage("Password");
        String password = view.getStringInput();
        for (User user: users) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                return new User(user.getId(), user.getName(), user.getPassword(), user.getUserType());
            }
        }
        view.showMessage("Wrong name/password");
        return null;
    }

    public void chooseController(User user) {
        if (user.getUserType().equals("ADMIN")) {
            AdminController adminController = new AdminController();
        } else if (user.getUserType().equals("CUSTOMER")) {
            CustomerController customerController = new CustomerController();
            customerController.handleCustomerController();
        }
    }



    
}