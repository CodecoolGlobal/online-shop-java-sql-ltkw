package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.service.CustomerService;
import com.codecool.onlineshop.view.View;

public class CustomerController {
    
    private View view = new View();
    private User customer;
    private boolean isRunning;
    private CustomerService customerService;

    public CustomerController() {

    }

    public void handleCustomerController() {
        while (isRunning) {
            view.displayCustomerMenu();

        }
    }

    public void handleCustomerinput() {
        int userInput = view.getIntegerInput();
        switch (userInput) {
            case 1:
                customer.getBasket().getProducts().toString();
                break;
            case 2:
                break;
            case 3:
                handleDeleteProduct();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                isRunning = false;
                break;
            default:
                break;

        }
    }

    public void handleDeleteProduct() {
        view.showMessage("Enter product id you want to remove");
        int id = view.getIntegerInput();
        customerService.removeProductFromBasket(id);
    }

    
}