package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.Product;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ProductsDaoImpl implements ProductDao {
    private final String CONNECTIONSQL = "jdbc:sqlite:src/main/resources/databases/OnlineShop.db";
    private final String SELECTDATA = "SELECT * FROM Products;";
    private Product product;
    List<Product> products;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public ProductsDaoImpl () {
        products = new ArrayList<Product>();
        getProductData();
    }


    public List<Product> getProductData() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTIONSQL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECTDATA);

            while (resultSet.next()) {
                int id = resultSet.getInt("productID");
                String name = resultSet.getString("Name");
                String category = resultSet.getString("Category");
                int price  = resultSet.getInt("Price");
                int amount = resultSet.getInt("Amount");
                System.out.println(name);//delete it later
                product = new Product(id, name, category, price, amount);
                products.add(product);
            }
            resultSet.close();
            statement.close();
            connection.close();        
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
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTIONSQL);
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

    public Integer getProductsSize() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public void updateProduct(Product product) {

    }

    public void deleteProduct(Product product){

    }

}