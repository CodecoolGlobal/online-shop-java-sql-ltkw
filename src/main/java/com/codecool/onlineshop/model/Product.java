package com.codecool.onlineshop.model;

public class Product {
    
    private int id;
    private String name;
    private String category;
    private int price;
    private int amount;    
    private int rating;
    private boolean isAvailable;

    public Product(int id, String name, String category, int price, int amount, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.amount = amount;        
        this.isAvailable = isAvailable;
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

    public boolean checkIfAvailable() {
        return isAvailable;
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

    public void setIfAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
	public String toString() {
		return  "id=" + id + "\n" +
                ", name='" + name + '\'' + "\n" +
                ", category=" + category + "\n" +
				", price=" + price + "\n" +
				", amount=" + amount + "\n" +
				", isAvailable=" + isAvailable + "\n" +
				"}\n";
	}

}