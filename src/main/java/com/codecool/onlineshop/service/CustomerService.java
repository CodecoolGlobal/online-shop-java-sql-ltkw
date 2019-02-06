package com.codecool.onlineshop.service;

import java.util.Iterator;

import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.User;

public class CustomerService {

    private User user;
    private Iterator<Product> iterator;

    public CustomerService(User user) {
        this.user = user;
        this.iterator = user.getBasket().getIterator();
    }

    // public void addProductToBasket(Product product, int amount) {
    // user.getBasket().addProduct(product, amount);
    // }

    public void removeProductFromBasket(int id) {
        if (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                user.getBasket().deleteProduct(iterator.next());
            }
        }
    }

    public void DisplayAllProducts() {
        if (iterator.hasNext()) {
            iterator.next().toString();
        }
    }

    public User getUser() {
        return this.user;
    }


}