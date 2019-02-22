package com.codecool.onlineshop.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.codecool.onlineshop.model.Order;

public class OrdersHistoryDaoImpl implements OrdersHistoryDao {
    private Connection connection;
    private Statement statement;
    private List<Order> orderHistoryDetails;

    public OrdersHistoryDaoImpl() {
        orderHistoryDetails = new ArrayList<>();
        addOrderHistory();
        addOrderHistoryData();
    }
    
    @Override
    public List<Order> getOrderHistoryDetails() {
        return orderHistoryDetails;
    }

    private void addOrderHistory() {
        connection = initializeConnection();
        String clearTable = "DELETE FROM AllOrders;";
        String insertToOrderHistory = "INSERT INTO AllOrders (OrderID, Date, UserID, TotalPrice, Status)"
                                    + "SELECT OrderID, Date, UserID, SUM(ProductAmountPrice), Status "
                                    + "FROM OrderDetails GROUP BY OrderID;";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(clearTable);
            statement.executeUpdate(insertToOrderHistory);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection initializeConnection() {
        final String DATABASEPATH = "src/main/resources/databases/OnlineShop.db";
        Connector connector = new Connector(DATABASEPATH);
        return connector.getDatabaseConnection();
    }

    private void addOrderHistoryData() {
        connection = initializeConnection();
        Order order;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM AllOrders;");
            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                String date = resultSet.getString("Date");
                int userId = resultSet.getInt("UserID");
                int totalPrice = resultSet.getInt("TotalPrice");
                String status = resultSet.getString("Status");
                
                order = new Order.Builder()
                                 .withOrderId(orderId)
                                 .withDate(date)
                                 .withUserId(userId)
                                 .withTotalPrice(totalPrice)
                                 .withStatus(status)
                                 .build();
                orderHistoryDetails.add(order);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}