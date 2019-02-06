package com.codecool.onlineshop.dao;

import java.util.List;

import com.codecool.onlineshop.model.Order;

public interface OrdersDao {

    public List<Order> getOrderData();
    public Order getOrder(int id);
    public void updateOrder(int orderId, String columnName, String newUpdate);
    public void deleteOrder(int orderId);
    public void addOrder(Order order);
}