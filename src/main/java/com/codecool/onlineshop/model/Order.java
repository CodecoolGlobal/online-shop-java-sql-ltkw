package com.codecool.onlineshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Integer orderId;
    private int userID;
    private OrderStatus orderStatus;
    private List<Order> orders;
    private int orderSumPrice;

    public Order(int orderId, int productId, int productAmount, int productAmountPrice, int userID) {
        this.orderId = orderId;
        this.userID = userID;
        this.orderStatus = OrderStatus.SUBMIT;
        orders = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
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