package com.codecool.onlineshop.service;

import java.util.Iterator;

import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.User;

public class CustomerService {

    User user;

    public CustomerService(User user) {
        this.user = user;
    }

    // public void addProductToBasket(Product product, int amount) {
    // user.getBasket().addProduct(product, amount);
    // }

    public void removeProductFromBasket(Product product) {
        user.getBasket().deleteProduct(product);
    }

    public void DisplayAllProducts() {
        Iterator<Product> iterator = user.getBasket().getIterator();
        if (iterator.hasNext()) {
            iterator.next().toString();
        }
    }
}