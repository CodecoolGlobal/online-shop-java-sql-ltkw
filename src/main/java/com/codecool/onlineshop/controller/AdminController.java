package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.view.View;
import com.codecool.onlineshop.dao.*;
import com.codecool.onlineshop.service.AdminService;

public class AdminController {
    private View view;
    private boolean isRunning;
    private ProductsDaoImpl productsDao;
    private AdminService adminService;

    public AdminController() {
        view = new View();
        productsDao = new ProductsDaoImpl();
    }

    public void handleAdminController() {
        while (isRunning) {
            view.displayAdminMenu();
        }
    }

    public void handleCustomerinput() {
        int userInput = view.getIntegerInput();
        switch (userInput) {
            case 1:
                adminService.createNewProduct();;
                break;
            case 2:
                adminService.deleteProduct();
                break;
            case 3:
                view.displayNotImplementedMessage();
                break;
            case 4:
                adminService.editProductPrice();
                break;
            case 5:
                adminService.editProductName();
                break;
            case 6:
                view.displayNotImplementedMessage();
                break;
            case 7:
                view.displayNotImplementedMessage();
                break;
            case 8:
                isRunning = false;
            default:
                break;

        }
    }

}