package com.codecool.onlineshop.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.codecool.onlineshop.dao.Connector;
import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.User;

public class OrdersDaoImpl implements OrdersDao {
    private Connector connector;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Order order;
    private List<Order> orders;

    public OrdersDaoImpl() {
        connector = new Connector("src/main/resources/databases/OnlineShop.db");
        connection = connector.getDatabaseConnection();
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
        Product currentProduct;
        int productId;
        int productAmount;
        int productAmountPrice;
        int userId;
        Integer orderId;
        String productName;

        if (orders.isEmpty()) {
            orderId = 1;
        } else {
            orders.sort(Order::compareTo);
            orderId = orders.get(orders.size()-1).getOrderId() + 1;
        }

        while (basketIterator.hasNext()) {
            currentProduct = basketIterator.next();
            productId = currentProduct.getId();
            productName = currentProduct.getName();
            productAmount = currentProduct.getAmount();
            productAmountPrice = currentProduct.getAmount() * currentProduct.getPrice();
            userId = user.getId();

            try {
                statement = connection.createStatement();
                String insertInto = "INSERT INTO OrderDetails (OrderID, ProductID, ProductName, ProductAmount, ProductAmountPrice, UserID) VALUES ("
                        + orderId + "," + productId + ",\"" + productName + "\"," + productAmount + "," + productAmountPrice + "," + userId
                        + ");";
                statement.executeUpdate(insertInto);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Order> addOrderData() {

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrderDetails;");
            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                int productAmount = resultSet.getInt("ProductAmount");
                int productAmountPrice = resultSet.getInt("ProductAmountPrice");
                int userId = resultSet.getInt("UserID");

                order = new Order(orderId, productId, productName, productAmount, productAmountPrice, userId);
                orders.add(order);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}