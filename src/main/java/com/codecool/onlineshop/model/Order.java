package com.codecool.onlineshop.model;

public class Order {

    private int id;
    private Basket basket;
    private User user;
    private String orderStatus;

    public Order(int id, Basket basket, User user, String orderStatus) {
        this.id = id;
        this.basket = basket;
        this.user = user;
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public Basket getBasket() {
        return basket;
    }

    public User getUser() {
        return user;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}