package com.codecool.onlineshop.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.codecool.onlineshop.dao.Connector;
import com.codecool.onlineshop.model.Order;

public class OrdersHistoryDaoImpl implements OrdersHistoryDao {
    private Connector connector;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Order order;
    private List<Order> orderHistoryDetails;

    public OrdersHistoryDaoImpl() {
        connector = new Connector("src/main/resources/databases/OnlineShop.db");
        connection = connector.getDatabaseConnection();
        orderHistoryDetails = new ArrayList<>();
        addOrderHistory();
        addOrderHistoryData();
    }
    
    @Override
    public List<Order> getOrderHistoryDetails() {
        return orderHistoryDetails;
    }

    private void addOrderHistory() {

        try {
            statement = connection.createStatement();
            String clearTable = "DELETE FROM AllOrders;";
            statement.executeUpdate(clearTable);
            String insertToOrderHistory = "INSERT INTO AllOrders (OrderID, Date, UserID, TotalPrice)"
                                        + "SELECT OrderID, Date, UserID, SUM(ProductAmountPrice)"
                                        + "FROM OrderDetails GROUP BY OrderID;";
                                        
            statement.executeUpdate(insertToOrderHistory);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Order> addOrderHistoryData() {

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM AllOrders;");
            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                String date = resultSet.getString("Date");
                int userId = resultSet.getInt("UserID");
                int totalPrice = resultSet.getInt("TotalPrice");
                
                order = new Order.Builder()
                                 .withOrderId(orderId)
                                 .withDate(date)
                                 .withUserId(userId)
                                 .withTotalPrice(totalPrice)
                                 .build();
                orderHistoryDetails.add(order);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryDetails;
    }
}