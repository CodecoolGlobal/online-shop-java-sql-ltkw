package com.codecool.onlineshop;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private int id;
    private List<Product> products;

    public Basket(int id) {
        this.products = new ArrayList<Product>();
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getId() {
        return id;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }
}