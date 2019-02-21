package com.codecool.onlineshop.model;

public class Product {
    
    private int id;
    private String name;
    private String category;
    private int price;
    private int amount;    
    private int rating;
    private int numberOfRatings;

    public Product(int id, String name, String category, int price, int amount, int rating, int numberOfRatings) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.amount = amount;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean productIsAvailable() {
        return amount > 0;
    }

    @Override
	public String toString() {
		return  "id = " + id +
                ", \nname = " + name +
                ", \ncategory = " + category +
				", \nprice = " + price +
				", \namount = " + amount;
	}

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public double calculateRating() {
        double ratings;
        if (this.numberOfRatings == 0) {
            ratings = rating / 1;
        } else {
            ratings = (double) rating / numberOfRatings;
        }
        return ratings;
    }
}