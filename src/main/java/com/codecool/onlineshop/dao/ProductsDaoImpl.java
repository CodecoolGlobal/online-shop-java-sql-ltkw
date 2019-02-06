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

    public ProductsDaoImpl () {
        products = new ArrayList<Product>();
        getProductData();
    }


    public List<Product> getProductData() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTIONSQL);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTDATA);

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



    public Product getProduct(int id) {
        return products.get(id);
    }

    public void updateProduct(Product product) {

    }

    public void deleteProduct(Product product){

    }

    public void addNewProduct(int id, String name, String category, int price, int amount) {
        
        
    }
}