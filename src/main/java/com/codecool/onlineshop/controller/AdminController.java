package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.view.View;
import com.codecool.onlineshop.service.AdminService;

public class AdminController {
    private View view;
    private boolean isRunning;
    private AdminService adminService;

    public AdminController() {
        view = new View();
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
        view.clearScreen();
        switch (userInput) {
            case 1:
                adminService.createNewProduct();
                break;
            case 2:
                adminService.displayAllProductsInShop();
                adminService.deleteProductAdmin();
                break;
            case 3:
                adminService.displayAllProductsInShop();
                view.getEmptyInput();
                break;
            case 4:
<<<<<<< HEAD
                view.clearScreen();
=======
>>>>>>> 56d4e1f41de8549fadc2c1929ea94ac6debda3dc
                adminService.displayAllProductsInShop();
                adminService.editProductPrice();
                break;
            case 5:
<<<<<<< HEAD
                view.clearScreen();
=======
>>>>>>> 56d4e1f41de8549fadc2c1929ea94ac6debda3dc
                adminService.displayAllProductsInShop();
                adminService.editProductName();
                break;
            case 6:
                adminService.displayAllProductsInShop();
                adminService.editProductAmount();
                break;
            case 7:
                adminService.displayAllOrders();
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