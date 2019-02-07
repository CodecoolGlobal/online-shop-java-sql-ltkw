package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.Product;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ProductsDaoImpl implements ProductDao {
    private final String CONNECTIONSQL = "src/main/resources/databases/OnlineShop.db";
    private Product product;
    private List<Product> products;
    private Connector connector;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ProductsDaoImpl () {
        connector = new Connector(CONNECTIONSQL);
        connection = connector.getDatabaseConnection();
        products = new ArrayList<Product>();
        getProductData();
    }


    public List<Product> getProductData() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Products;");

            while (resultSet.next()) {
                int id = resultSet.getInt("productID");
                String name = resultSet.getString("Name");
                String category = resultSet.getString("Category");
                int price  = resultSet.getInt("Price");
                int amount = resultSet.getInt("Amount");
                product = new Product(id, name, category, price, amount);
                products.add(product);
            }
            resultSet.close();
            statement.close();       
        } catch (Exception error) {
            System.err.println(error.getClass().getName() + ": " 
                            + error.getMessage() );
            System.exit(0);
        }
        return products;
    }

    public void addNewProduct(String name, String category, String price, String amount) {
        int productID = getProductsSize() + 1;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "INSERT INTO Products (productID,Name,Category,Price,Amount) " +
                        "VALUES ( " + productID + "," + "'" + name + "'" + "," + 
                        "'" + category + "'" + "," + price + "," + 
                        amount +" );";
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
            connection.close();        
        } catch (Exception error) {
            System.err.println(error.getClass().getName() + ": " 
                            + error.getMessage() );
            System.exit(0);
        }
    }

    public void deleteProduct(String productID) {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "DELETE FROM Products WHERE productID = " + 
                            productID + ";";
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();       
        } catch (Exception error) {
            System.err.println(error.getClass().getName() + ": " 
                            + error.getMessage() );
            System.exit(0);
        }
    }


    public void editProductPrice(String productID, String productPrice) {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "UPDATE Products SET Price = " + productPrice + 
                            " WHERE productID = " + productID + ";";
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();       
        } catch (Exception error) {
            System.err.println(error.getClass().getName() + ": " 
                            + error.getMessage() );
            System.exit(0);
        }
    }
    public void editProductName(String productID, String productName){
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "UPDATE Products SET Name = " + productName +
                    " WHERE productID = " + productID + ";";
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
        } catch (Exception error) {
            System.err.println(error.getClass().getName() + ": "
                    + error.getMessage() );
            System.exit(0);
        }

    }


    public Integer getProductsSize() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

}