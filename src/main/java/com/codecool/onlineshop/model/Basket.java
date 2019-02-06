package com.codecool.onlineshop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Basket {

    private int id;
    private List<Product> products;
    private Iterator<Product> iterator;

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

    public void addProduct(Product product, int amount) {
        product.setAmount(amount);
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public Iterator<Product> getIterator() {
        return this.iterator;
    }
}