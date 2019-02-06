package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.view.View;

public class LoginController {

    private View view = new View();
    boolean isRunning = true;

    public void startLogin() {
        view.displayMainMenu();
        handleLogin();
    }

    public void handleLogin() {
        int userInput = view.getIntegerInput();
        switch (userInput) {
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    
}