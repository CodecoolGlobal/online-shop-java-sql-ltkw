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
    private final String COLUMNAMOUNT = "Amount";
    

    public ProductsDaoImpl () {
        products = new ArrayList<Product>();
        getProductData();
    }

    private List<Product> getProductData() {
        connector = new Connector(CONNECTIONSQL);
        connection = connector.getDatabaseConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Products;");
            
            while (resultSet.next()) {
                int id = resultSet.getInt("productID");
                String name = resultSet.getString("Name");
                String category = resultSet.getString("Category");
                int price  = resultSet.getInt("Price");
                int amount = resultSet.getInt("Amount");
                int rating = resultSet.getInt("Rating");
                int numberOfRatings = resultSet.getInt("RatingsAmount");
                product = new Product.Builder()
                                     .withId(id)
                                     .withName(name)
                                     .withCategory(category)
                                     .withPrice(price)
                                     .withAmount(amount)
                                     .withRating(rating)
                                     .withNumberOfRatings(numberOfRatings)
                                     .build();
                products.add(product);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException error) {
             error.printStackTrace();
        }
        return products;
    }

    private void createSqlStatement(String sqlStatement) {
        Connector connector = new Connector(CONNECTIONSQL);
        Connection connection = connector.getDatabaseConnection();
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
    public void addNewProduct(String name, String category, int price, int amount) {
        int productID = Integer.valueOf(getNextPrimaryKey());
        String amountRating = "0";
        String ratingsAmount = "0";
        String sql = "INSERT INTO Products (productID,Name,Category,Price,Amount,Rating,RatingsAmount) " +
                        "VALUES ( " + productID + "," + "'" + name + "'" + "," +
                        "'" + category + "'" + "," + price + "," +
                        amount + "," + amountRating + "," + ratingsAmount + " );";
        createSqlStatement(sql);
    }

    @Override
    public void deleteProductAdmin(int productID) {
        String sql = "DELETE FROM Products WHERE productID = " +
                            productID + ";";
        createSqlStatement(sql);
    }

    private Integer addProduct(int productID, int productAmount) {
        int lastAmount = 0;
        shopIterator = products.iterator();
        while (shopIterator.hasNext()) {
            Product currentProduct = shopIterator.next();
            if (currentProduct.getId() == productID){
                lastAmount = currentProduct.getAmount() + productAmount;
            }
        }
        return lastAmount;
    }

    private Integer deleteProduct(int productID, int productAmount) {
        int lastAmount = 0;
        for (Product product : products) {
            if(productID == product.getId()){
                lastAmount = product.getAmount() - productAmount;
            }
        }
        return lastAmount;
    }

    private Integer getNextPrimaryKey() {
        int result = 1;
        for(Product product : products) {
            int productId = product.getId();
            if (productId > result) {
                result = productId;
            }
        }
        result += 1;
        return result;
    }

    @Override
    public boolean validID(int id) {
        int productID = id;
        for (Product product : products) {
            if(product.getId() == productID) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean productAmountIsValid(int productID, int productAmount) {
        //int id = Integer.parseInt(productID);
        //int amount = Integer.parseInt(productAmount);
        for (Product el : products) {
            if(productID == el.getId()) {
                if (productAmount <= el.getAmount()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void addProductByUser(int productID, int productAmount) {
        int lastAmount =  addProduct(productID, productAmount);
        editProduct(productID, lastAmount, COLUMNAMOUNT);
    }

    @Override
    public void deleteProductsByUser(int productID, int productAmount) {
        int lastAmount = deleteProduct(productID, productAmount);
        editProduct(productID, lastAmount, COLUMNAMOUNT);
    }

    public void editProduct(int productID, int productChange, String column) {
        String sql = "UPDATE Products SET " + column + " = " + productChange + " WHERE productID = " + productID + ";";
        createSqlStatement(sql);
    }

    @Override
    public void editProductRating(int productID, int rating, String column) {
        editProduct(productID, rating, column);
    }

    @Override
    public void editProductNumberOfRatings(int productID, int numberOfRatings, String column) {
        editProduct(productID, numberOfRatings, column);
    }

    @Override
    public void editProductName(int productID, String productName){
        String sql = "UPDATE Products SET Name = " + "'" + productName + "'" + " WHERE productID = " + productID + ";";
        createSqlStatement(sql);
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}