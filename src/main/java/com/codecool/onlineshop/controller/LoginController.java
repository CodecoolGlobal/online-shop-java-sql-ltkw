package com.codecool.onlineshop.controller;

import java.util.List;

import com.codecool.onlineshop.dao.UsersDaoImpl;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.View;

public class LoginController {

    private View view;
    boolean isRunning = true;
    private UsersDaoImpl usersDao;

    public LoginController() {
        this.view = new View();
        this.isRunning = true;
        this.usersDao = new UsersDaoImpl();
    }

    public void startLoginController() {
        view.clearScreen();
        handleLoginController();
    }

    public void handleLoginController() {
        while (isRunning) {
            view.displayMainMenu();
            int userInput = view.getIntegerInput();
            switch (userInput) {
                case 1:
                    List<User> users = usersDao.getUserData();
                    chooseController(handleLogin(users));
                    break;
                case 2:
                    handleAddNewUser();
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    view.showMessage("Wrong input!");
                    break;
            }
        }
    }

    public User handleLogin(List<User> usersList) {
        boolean isLogging = true;
        this.usersDao = new UsersDaoImpl();
        List<User> users = usersList;
        while (isLogging) {
            view.showMessage("Name: ");
            String name = view.getStringInput();
            view.showMessage("Password:");
            String password = view.getStringInput();
            for (User user: users) {
                if(user.getName().equals(name) && user.getPassword().equals(password)) {
                    return new User(user.getId(), user.getName(), user.getPassword(), user.getUserType());
                }
            }
            view.showMessage("Wrong name/password");
            continue;
        }
        return null;
    }

    public void chooseController(User user) {
        view.clearScreen();
        if (user.getUserType().equals("ADMIN")) {
            view.showMessage("asdsfasdfadf");
            AdminController adminController = new AdminController();
            adminController.handleAdminController();
        } else if (user.getUserType().equals("CUSTOMER")) {
            CustomerController customerController = new CustomerController(user);
            customerController.handleCustomerController();
        }
    }

    public void handleAddNewUser() {
        view.showMessage("Name: ");
        String name = view.getStringInput();
        view.showMessage("Password: ");
        String password = view.getStringInput();
        usersDao.addUser(name, password, "CUSTOMER");
    }  
}