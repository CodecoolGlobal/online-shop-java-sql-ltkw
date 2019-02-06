package com.codecool.onlineshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codecool.onlineshop.dao.ProductsDaoImpl;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.View;

public class CustomerService {

    private User user;
    private Iterator<Product> iterator;
    View view = new View();

    public CustomerService(User user) {
        this.user = user;
        this.iterator = user.getBasket().getIterator();
    }

    // public void addProductToBasket(Product product, int amount) {
    // user.getBasket().addProduct(product, amount);
    // }

    public void removeProductFromBasket(int id) {
        if (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                user.getBasket().deleteProduct(iterator.next());
            }
        }
    }

    public void DisplayAllProducts() {
        if (iterator.hasNext()) {
            iterator.next().toString();
        }
    }

    public User getUser() {
        return this.user;
    }

    public void editProductquantity (){
        view.showMessage("enter product name whose quantity you want to change");
        String name = view.getStringInput();
        for (Product p: user.getBasket().getProducts()){
            if(p.getName().contains(name)){
                view.showMessage("enter a new quantity");
                p.setAmount(view.getIntegerInput());
            }
        }

    }


    public void getProductsBycategory(){
        ProductsDaoImpl productsDao = new ProductsDaoImpl();
        view.showMessage("enter category name ");
        String name = view.getStringInput();
        ArrayList<Product> productsByCategory = new ArrayList<>();
        while (productsDao.getProductData().iterator().hasNext()){
            Product product = productsDao.getProductData().iterator().next();
            if (product.getCategory().contains(name)){
                productsByCategory.add(product);
            }

        }
    }


}