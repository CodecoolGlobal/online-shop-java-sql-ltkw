package com.codecool.onlineshop.main;

import com.codecool.onlineshop.controller.LoginController;
import com.codecool.onlineshop.dao.ProductsDaoImpl;

public class Main {
    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        loginController.startLogin();
        // System.out.println("Hello World!");
        // ProductsDaoImpl product = new ProductsDaoImpl();
        // product.getProductData();
    }
}