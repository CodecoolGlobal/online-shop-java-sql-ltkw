package com.codecool.onlineshop.model;

public class Order {

    private int id;
    private Basket basket;
    private int userID;
    private OrderStatus orderStatus;

    public Order(int id, Basket basket, int userID, String orderStatus) {
        this.id = id;
        this.basket = basket;
        this.userID = userID;
        this.orderStatus = OrderStatus.SUBMIT;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
}