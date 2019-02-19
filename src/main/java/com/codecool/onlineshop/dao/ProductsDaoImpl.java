package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.Product;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class ProductsDaoImpl implements ProductDao {
    private final String CONNECTIONSQL = "src/main/resources/databases/OnlineShop.db";
    private Product product;
    private List<Product> products;
    private Connector connector;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Iterator<Product> shopIterator;
    

    public ProductsDaoImpl () {
        connector = new Connector(CONNECTIONSQL);
        connection = connector.getDatabaseConnection();
        products = new ArrayList<Product>();
        getProductData();
    }

    private List<Product> getProductData() {
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
        } catch (SQLException error) {
             error.printStackTrace();
        }
        return products;
    }

    private void createSqlStatement(String sqlStatement) {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = sqlStatement;
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    @Override
    public void addNewProduct(String name, String category, String price, String amount) {
        int productID = getProductsSize() + 1;
        String sql = "INSERT INTO Products (productID,Name,Category,Price,Amount) " +
                        "VALUES ( " + productID + "," + "'" + name + "'" + "," +
                        "'" + category + "'" + "," + price + "," +
                        amount +" );";
        createSqlStatement(sql);
    }

    @Override
    public void deleteProductAdmin(String productID) {
        String sql = "DELETE FROM Products WHERE productID = " +
                            productID + ";";
        createSqlStatement(sql);
    }

    @Override
    public void deleteProductsByUser(String productID, String productAmount) {
        int amount = Integer.valueOf(productAmount);
        int productId = Integer.valueOf(productID);
        int lastAmout = 0;
        shopIterator = products.iterator();
        while (shopIterator.hasNext()) {   
            Product currentProduct = shopIterator.next();      
            if (currentProduct.getId() == productId){
                lastAmout = currentProduct.getAmount() - amount;
            }
        }

        String sql = "UPDATE Products SET Amount = " + lastAmout +
                " WHERE productID = " + productID + ";";
        createSqlStatement(sql);
    }

    @Override
    public void editProductPrice(String productID, String productPrice) {
        String sql = "UPDATE Products SET Price = " + productPrice +
                " WHERE productID = " + productID + ";";
        createSqlStatement(sql);
    }


    @Override
    public void editProductName(String productID, String productName){
        String sql = "UPDATE Products SET Name = " + "'" + productName + "'" +
                " WHERE productID = " + productID + ";";
        createSqlStatement(sql);
    }

    @Override
    public void editProductAmount(String productID, String productAmount){
        String sql = "UPDATE Products SET Amount = " + productAmount +
                " WHERE productID = " + productID + ";";
        createSqlStatement(sql);
    }

    @Override
    public Integer getProductsSize() {
        return products.size();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product getProduct(int id) {
        return products.get(id);
    }

}