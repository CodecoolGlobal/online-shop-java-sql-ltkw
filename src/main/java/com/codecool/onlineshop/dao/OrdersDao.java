package com.codecool.onlineshop.dao;

import java.util.List;

import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.User;

public interface OrdersDao {

    public List<Order> getOrderData();
    public Order getOrder(int id);
    public void addOrder(User user);
}