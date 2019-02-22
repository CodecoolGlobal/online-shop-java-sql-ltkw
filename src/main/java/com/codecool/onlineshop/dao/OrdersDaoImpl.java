package com.codecool.onlineshop.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.OrderStatus;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.User;

public class OrdersDaoImpl implements OrdersDao {

    private Connection connection;
    private List<Order> orders;

    public OrdersDaoImpl() {
        orders = new ArrayList<>();
        addOrderData();
    }

    @Override
    public List<Order> getOrderData() {
        return orders;
    }

    @Override
    public void addOrder(User user) {
        Iterator<Product> basketIterator = user.getBasket().getIterator();
        LocalDate localDate = LocalDate.now();

        connection = initializeConnection();
        PreparedStatement insertOrder;
        String insertOrderString = "INSERT INTO OrderDetails"
                                 + "(OrderID, ProductID, ProductName, ProductAmount, ProductAmountPrice, UserID, Date, Status)"
                                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String date = localDate.getDayOfMonth() + "-" + localDate.getMonthValue() + "-" + localDate.getYear();
        int orderId = getIncrementedOrderId();
        while (basketIterator.hasNext()) {
            Product currentProduct = basketIterator.next();
            String productName = currentProduct.getName();
            int productId = currentProduct.getId();
            int productAmount = currentProduct.getAmount();
            int productAmountPrice = currentProduct.getAmount() * currentProduct.getPrice();
            int userId = user.getId();

            try {
                insertOrder = connection.prepareStatement(insertOrderString);
                insertOrder.setInt(1, orderId);
                insertOrder.setInt(2, productId);
                insertOrder.setString(3, productName);
                insertOrder.setInt(4, productAmount);
                insertOrder.setInt(5, productAmountPrice);
                insertOrder.setInt(6, userId);
                insertOrder.setString(7, date);
                insertOrder.setString(8, OrderStatus.PENDING.name());
                insertOrder.executeUpdate();
                insertOrder.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        connection = initializeConnection();
        PreparedStatement updateOrderStatus;
        String updateOrderStatusString = "UPDATE OrderDetails SET Status = ? WHERE OrderID = ?";
        try {
            updateOrderStatus = connection.prepareStatement(updateOrderStatusString);
            updateOrderStatus.setInt(2, orderId);
            updateOrderStatus.setString(1, status);
            updateOrderStatus.executeUpdate();
            updateOrderStatus.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isValid(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return true;
            }
        }
        return false;
    }

    private int getIncrementedOrderId() {
        int orderId;

        if (orders.isEmpty()) {
            orderId = 1;
        } else {
            orders.sort(Order::compareTo);
            orderId = orders.get(orders.size()-1).getOrderId() + 1;
        }
        return orderId;
    }

    private Connection initializeConnection() {
        final String DATABASEPATH = "src/main/resources/databases/OnlineShop.db";
        Connector connector = new Connector(DATABASEPATH);
        return connector.getDatabaseConnection();
    }

    private void addOrderData() {
        connection = initializeConnection();
        Order order;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM OrderDetails;");
            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                int productAmount = resultSet.getInt("ProductAmount");
                int productAmountPrice = resultSet.getInt("ProductAmountPrice");
                int userId = resultSet.getInt("UserID");
                String date = resultSet.getString("Date");
                String status = resultSet.getString("Status");

                order = new Order.Builder()
                                 .withOrderId(orderId)
                                 .withProductId(productId)
                                 .withProductName(productName)
                                 .withProductAmount(productAmount)
                                 .withProductAmountPrice(productAmountPrice)
                                 .withUserId(userId)
                                 .withDate(date)
                                 .withStatus(status)
                                 .build();
                orders.add(order);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}