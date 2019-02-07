package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.view.View;
import com.codecool.onlineshop.dao.*;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.service.AdminService;

public class AdminController {
    private View view;
    private boolean isRunning;
    private ProductsDaoImpl productsDao;
    private AdminService adminService;

    public AdminController() {
        view = new View();
        //productsDao = new ProductsDaoImpl();
        isRunning = true;
        this.adminService = new AdminService();
    }

    public void handleAdminController() {
        while (isRunning) {
            view.clearScreen();
            view.displayAdminMenu();
            handleAdminInput();
        }
    }

    public void handleAdminInput() {
        int userInput = view.getIntegerInput();
        switch (userInput) {
            case 1:
                view.clearScreen();
                adminService.createNewProduct();;
                break;
            case 2:
                view.clearScreen();
                adminService.displayAllProductsInShop();
                adminService.deleteProductAdmin();
                break;
            case 3:
                view.clearScreen();
                view.displayNotImplementedMessage();
                break;
            case 4:
                view.clearScreen();
                adminService.editProductPrice();
                break;
            case 5:
                view.clearScreen();
                adminService.editProductName();
                break;
            case 6:
                view.clearScreen();
                adminService.editProductAmount();
                break;
            case 7:
                view.clearScreen();
                view.displayNotImplementedMessage();
                break;
            case 8:
                view.displayNotImplementedMessage();
                break;
            case 9:
                isRunning = false;
                break;
            default:
                break;

        }
    }

}