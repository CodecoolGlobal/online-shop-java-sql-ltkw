package com.codecool.onlineshop.model;

public class Order {

    private Integer orderId;
    private int userID;
    private int productId;
    private String productName;
    private int productAmount;
    private int productAmountPrice;

    public Order(int orderId, int productId, String productName, int productAmount, int productAmountPrice, int userID) {
        this.orderId = orderId;
        this.userID = userID;
        this.productAmount = productAmount;
        this.productId = productId;
        this.productAmountPrice = productAmountPrice;
        this.productName = productName;
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

    public int compareTo(Order other) {
        return this.orderId.compareTo(other.orderId);
    }
}