package com.codecool.onlineshop.model;

public class Product {
    
    private int id;
    private String name;
    private String category;
    private int price;
    private int amount;
    private int rating;
    private int numberOfRatings;

    public Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.category = builder.category;
        this.price = builder.price;
        this.amount = builder.amount;
        this.rating = builder.rating;
        this.numberOfRatings = builder.numberOfRatings;
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

    public static class Builder {
        private int id;
        private String name;
        private String category;
        private int price;
        private int amount;
        private int rating;
        private int numberOfRatings;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder withNumberOfRatings(int numberOfRatings) {
            this.numberOfRatings = numberOfRatings;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}