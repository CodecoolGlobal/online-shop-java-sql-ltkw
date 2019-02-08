package com.codecool.onlineshop.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int id;
    private String name;
    private List<Product> products;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<Product>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void populateProducts() {
        
    }
}