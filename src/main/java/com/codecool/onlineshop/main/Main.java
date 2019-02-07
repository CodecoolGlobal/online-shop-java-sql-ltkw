package com.codecool.onlineshop.main;

import com.codecool.onlineshop.controller.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginController controller = new LoginController();
        controller.startLoginController();
    }
}