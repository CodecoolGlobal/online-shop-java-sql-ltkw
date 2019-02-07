package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.service.CustomerService;
import com.codecool.onlineshop.view.View;

public class CustomerController {
    
    private View view = new View();
    private boolean isRunning;
    private CustomerService customerService;
    User customer = new User(1, "test", "pass", "customer");

    public CustomerController() {
        this.isRunning = true;
        this.customerService = new CustomerService(customer);
    }

    public void handleCustomerController() {
        while (isRunning) {
            view.displayCustomerMenu();
            handleCustomerinput();

        }
    }

    public void handleCustomerinput() {
        int userInput = view.getIntegerInput();
        switch (userInput) {
            case 1:
                view.clearScreen();
                customerService.DisplayAllProductsInBasket();
                break;
            case 2:
                view.clearScreen();
                handleAddProduct();
                break;
            case 3:
                view.clearScreen();
                handleDeleteProduct();
                break;
            case 4:
                view.clearScreen();
                view.displayNotImplementedMessage();
                break;
            case 5:
                view.clearScreen();
                view.displayNotImplementedMessage();
                break;
            case 6:
                view.clearScreen();
                customerService.displayAllProductsInShop();
                break;
            case 7:
                view.clearScreen();
                customerService.displayProductsBycategory();
                break;
            case 8:
                view.clearScreen();
                customerService.editProductQuantity();
                break;
            case 9:
                isRunning = false;
                break;
            default:
                break;

        }
    }

    public void handleDeleteProduct() {
        view.showMessage("Enter product ID you want to remove");
        int id = view.getIntegerInput();
        customerService.removeProductFromBasket(id);
    }

    public void handleAddProduct() {
        view.showMessage("Enter product ID you want to add");
        int id = view.getIntegerInput();
        view.showMessage("Enter amount");
        int amount = view.getIntegerInput();
        customerService.addProductToBasket(id, amount);
    }

    
}