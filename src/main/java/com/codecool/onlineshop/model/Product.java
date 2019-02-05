package com.codecool.onlineshop.model;

public class Product {
    
    private int id;
    private String name;
    private double price;
    private int amount;
    private Category category;
    private int rating;
    private boolean isAvailable;

    public Product(String name, double price, int amount, Category category, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }

    public boolean checkIfAvailable() {
        return isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setIfAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}