package com.codecool.onlineshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codecool.onlineshop.dao.ProductsDaoImpl;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.ProductIterator;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.View;

public class CustomerService {

    private User user;
    private Iterator<Product> basketIterator;
    private Iterator<Product> shopIterator;
    private ProductsDaoImpl productDao;
    View view = new View();

    public CustomerService(User user) {
        this.user = user;
        this.basketIterator = user.getBasket().getIterator();
        this.productDao = new ProductsDaoImpl();
        this.shopIterator = new ProductIterator(productDao.getProducts());
    }

    public void addProductToBasket(int id, int amount) {
        while (shopIterator.hasNext()) {
            Product current = shopIterator.next();
            if (current.getId() == id) {
                user.getBasket().addProduct(current, amount);
            }
        }
    }

    public void removeProductFromBasket(int id) {
        while (basketIterator.hasNext()) {
            Product productToRemove = basketIterator.next();
            if (productToRemove.getId() == id) {
                user.getBasket().deleteProduct(productToRemove);
            }
        }
    }

    public void DisplayAllProductsInBasket() {
        while (basketIterator.hasNext()) {
            view.showMessage(basketIterator.next().toString());
        }
    }

    public void displayAllProductsInShop() {
        while (shopIterator.hasNext()) {
            view.showMessage(shopIterator.next().toString());
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