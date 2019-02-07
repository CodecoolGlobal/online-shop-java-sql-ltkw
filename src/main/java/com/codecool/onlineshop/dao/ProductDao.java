package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProductData();//
    public Product getProduct(int id);//
    public void deleteProductAdmin(String id);
    public void addNewProduct(String name, String category, String price, String amount);
    public Integer getProductsSize();
    public List<Product> getProducts();
}