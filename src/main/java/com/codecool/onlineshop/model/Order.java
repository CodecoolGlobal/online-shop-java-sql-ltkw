package com.codecool.onlineshop.model;

public class Order {

    private Integer orderId;
    private int userId;
    private int productId;
    private String productName;
    private int productAmount;
    private int productAmountPrice;
    private int totalPrice;
    private String date;

    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.userId = builder.userId;
        this.productAmount = builder.productAmount;
        this.productId = builder.productId;
        this.productAmountPrice = builder.productAmountPrice;
        this.productName = builder.productName;
        this.totalPrice = builder.totalPrice;
        this.date = builder.date;
    }

    public int getProductAmountPrice() {
        return productAmountPrice;
    }

    public int getProductAmount(){
        return productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductId(){
        return productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }
    
    public int getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public int compareTo(Order other) {
        return this.orderId.compareTo(other.orderId);
    }

    public static class Builder {
        private Integer orderId;
        private String date;
        private int userId;
        private int totalPrice;
        private int productId;
        private String productName;
        private int productAmount;
        private int productAmountPrice;
    
        public Builder withOrderId(Integer orderId) {
            this.orderId = orderId;
            return this;
        }
    
        public Builder withUserId(int userId) {
            this.userId = userId;
            return this;
        }
    
        public Builder withProductId(int productId) {
            this.productId = productId;
            return this;
        }
    
        public Builder withProductName(String productName) {
            this.productName = productName;
            return this;
        }
    
        public Builder withProductAmount(int productAmount) {
            this.productAmount = productAmount;
            return this;
        }
    
        public Builder withProductAmountPrice(int productAmountPrice) {
            this.productAmountPrice = productAmountPrice;
            return this;
        }
    
        public Builder withTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }
    
        public Builder withDate(String date) {
            this.date = date;
            return this;
        }
    
        public Order build() {
            return new Order(this);
        }
    }
}