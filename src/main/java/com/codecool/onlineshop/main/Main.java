package com.codecool.onlineshop.main;

import com.codecool.onlineshop.controller.LoginController;
import com.codecool.onlineshop.dao.ProductsDaoImpl;

public class Main {
    public static void main(String[] args) {
        // LoginController loginController = new LoginController();
        // loginController.startLogin();

        ProductsDaoImpl product = new ProductsDaoImpl();
        //product.getProductData();

        // String name = "Mango";
        // String category = "Juice";
        // int price = 6;
        // int amount = 21;
        // product.addNewProduct(name, category, price, amount);
    }
}