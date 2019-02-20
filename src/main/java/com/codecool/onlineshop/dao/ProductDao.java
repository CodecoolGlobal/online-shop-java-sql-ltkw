package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.Product;

import java.util.List;

public interface ProductDao {
    public void deleteProductAdmin(String id);
    public void deleteProductsByUser(String productID, String productAmount);
    public void addNewProduct(String name, String category, String price, String amount);
    public void editProductPrice(String productID, String productPrice);
    public void editProductName(String productID, String productName);
    public void editProductAmount(String productID, String productAmount);
    public void editProductRating(String ProductId, String rating);
    public void editProductNumberOfRatings(String ProductId, String numberOfRatings);
    public Integer getProductsSize();
    public List<Product> getProducts();
    public Product getProduct(int id);
}