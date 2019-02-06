package com.codecool.onlineshop.model;

public class Product {
    
    private int id;
    private String name;
    private String category;
    private int price;
    private int amount;    
    private int rating;

    public Product(int id, String name, String category, int price, int amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.amount = amount;        
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
        if(amount > 0) {
            return true;
        }
        return false; 
    }

    @Override
	public String toString() {
		return  "id = " + id + "\n" +
                ", name = " + name + '\'' + "\n" +
                ", category = " + category + "\n" +
				", price = " + price + "\n" +
				", amount = " + amount + "\n" +
				"}\n";
	}
}