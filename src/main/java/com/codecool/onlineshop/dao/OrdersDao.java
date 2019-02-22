package com.codecool.onlineshop.dao;

import java.util.List;

import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.User;

public interface OrdersDao {

    public List<Order> getOrderData();
    public void addOrder(User user);
    public void updateOrderStatus(int orderId, String status);
}