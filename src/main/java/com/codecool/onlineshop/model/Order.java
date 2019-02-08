package com.codecool.onlineshop.model;

public class Order {

    private Integer orderId;
    private int userID;
    private int productId;
    private String productName;
    private int productAmount;
    private int productAmountPrice;
    private int totalPrice;
    private String date;

    public Order(int orderId, int productId, String productName, int productAmount, int productAmountPrice, int userID, String date) {
        this.orderId = orderId;
        this.userID = userID;
        this.productAmount = productAmount;
        this.productId = productId;
        this.productAmountPrice = productAmountPrice;
        this.productName = productName;
        this.date = date;
    }

    public Order(int orderId, String date, int userId, int totalPrice) {
        this.orderId = orderId;
        this.userID = userId;
        this.totalPrice = totalPrice;
        this.date = date;
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

    public int getUserID() {
        return userID;
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

}