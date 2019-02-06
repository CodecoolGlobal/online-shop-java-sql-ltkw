package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProductData();
    public Product getProduct(int id);
    public void updateProduct(Product product);
    public void deleteProduct(Product product);
}