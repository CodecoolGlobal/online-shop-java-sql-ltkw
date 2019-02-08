package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.dao.OrdersDaoImpl;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.service.CustomerService;
import com.codecool.onlineshop.view.View;

public class CustomerController {
    
    private View view = new View();
    private boolean isRunning;
    private CustomerService customerService;
    User customer;

    public CustomerController(User customer) {
        this.customer = customer;
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
        view.clearScreen();
        switch (userInput) {
            case 1:
                customerService.DisplayAllProductsInBasket();
                break;
            case 2:
                customerService.displayAllProductsInShop();
                handleAddProduct();
                break;
            case 3:
                handleDeleteProduct();
                break;
            case 4:
                OrdersDaoImpl orders = new OrdersDaoImpl();
                orders.addOrder(customer);

                break;
            case 5:
                customerService.displayOrdersHistory();
                view.getEmptyInput();
                customerService.displayUserOrder();
                view.getEmptyInput();
                break;
            case 6:
                customerService.displayAllProductsInShop();
                break;
            case 7:
                customerService.displayProductsBycategory();
                break;
            case 8:
                customerService.DisplayAllProductsInBasket();
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