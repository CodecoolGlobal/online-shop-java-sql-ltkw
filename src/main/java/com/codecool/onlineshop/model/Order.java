package com.codecool.onlineshop.model;

import java.util.ArrayList;
import java.util.List;

import com.codecool.onlineshop.model.Basket;

public class Order {

    private Integer orderId;
    private Basket basket;
    private int userID;
    private OrderStatus orderStatus;
    private List<Order> orders;
    private int orderSumPrice;
    private int productId;
    private String productName;
    private int productAmount;
    private int productAmountPrice;

    public Order(int orderId, int productId, int productAmount, int productAmountPrice, int userID) {
        this.orderId = orderId;
        this.basket = basket;
        this.userID = userID;
        this.orderStatus = OrderStatus.SUBMIT;
        this.orderSumPrice = orderSumPrice;
        this.productAmount = productAmount;
        orders = new ArrayList<>();
        this.productId = productId;
        this.productAmountPrice = productAmountPrice;
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

    public Basket getBasket() {
        return basket;
    }

    public int getUserID() {
        return userID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setUser(int userID) {
        this.userID = userID;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int getOrderSumPrice() {
        return orderSumPrice;
    }

    public int compareTo(Order other) {
        return this.orderId.compareTo(other.orderId);
    }
}