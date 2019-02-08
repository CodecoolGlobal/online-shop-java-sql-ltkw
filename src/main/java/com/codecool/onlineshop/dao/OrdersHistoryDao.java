package com.codecool.onlineshop.dao;

import java.util.List;

import com.codecool.onlineshop.model.Order;

public interface OrdersHistoryDao {

    public List<Order> getOrderHistoryDetails();
}