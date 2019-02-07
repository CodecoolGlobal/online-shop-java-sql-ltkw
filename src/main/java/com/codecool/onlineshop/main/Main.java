package com.codecool.onlineshop.main;

import java.util.ArrayList;

import com.codecool.onlineshop.controller.CustomerController;
import com.codecool.onlineshop.controller.LoginController;
import com.codecool.onlineshop.dao.ProductsDaoImpl;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.ProductIterator;

public class Main {
    public static void main(String[] args) {
        CustomerController controller = new CustomerController();
        controller.handleCustomerController();


    }
}