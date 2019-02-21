package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.Product;

import java.util.List;

public interface ProductDao {
    void deleteProductAdmin(int id);
    void deleteProductsByUser(int productID, int productAmount);
    void addNewProduct(String name, String category, int price, int amount);
    void editProductName(int productID, String productName);
    void editProductRating(int productId, int rating, String columnName);
    void editProductNumberOfRatings(int ProductId, int numberOfRatings, String columnName);
    void addProductByUser(int productID, int productAmount);
    boolean productAmountIsValid(int productID, int productAmount);
    boolean validID(int id);
    List<Product> getProducts();
}