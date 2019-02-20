package com.codecool.onlineshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codecool.onlineshop.dao.OrdersDaoImpl;
import com.codecool.onlineshop.dao.OrdersHistoryDaoImpl;
import com.codecool.onlineshop.dao.ProductsDaoImpl;
import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.ProductIterator;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.View;

public class CustomerService {

    private User user;
    private Iterator<Product> basketIterator;
    private Iterator<Product> shopIterator;
    private ProductsDaoImpl productDao;
    private OrdersDaoImpl orderDao;
    private OrdersHistoryDaoImpl orderHistoryDao;
    private View view;

    public CustomerService(User user) {
        this.user = user;
        this.basketIterator = user.getBasket().getIterator();
        this.productDao = new ProductsDaoImpl();
        this.shopIterator = new ProductIterator(productDao.getProducts());
        this.view = new View();
        this.orderDao = new OrdersDaoImpl();
        this.orderHistoryDao = new OrdersHistoryDaoImpl();
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
        view.productsTable(user.getBasket().getProducts());
    }

    public void displayAllProductsInShop() {
        productDao = new ProductsDaoImpl();
        view.productsTable(productDao.getProducts());
    }

    public User getUser() {
        return this.user;
    }

    public void editProductQuantity (){
        view.showMessage("enter product name whose quantity you want to change");
        String name = view.getStringInput();
        for (Product p: user.getBasket().getProducts()){
            if(p.getName().contains(name)){
                view.showMessage("enter a new quantity");
                p.setAmount(view.getIntegerInput());
            }
        }
    }


    public void displayProductsBycategory(){
        ProductsDaoImpl productsDao = new ProductsDaoImpl();
        view.showMessage("enter category name ");
        String name = view.getStringInput();
        ArrayList<Product> productsByCategory = new ArrayList<>();
        for(Product p: productsDao.getProducts()){
            Product product = p;
            if (product.getCategory().contains(name)){
                productsByCategory.add(product);
            }
        }
        view.productsTable(productsByCategory);
    }

    public void displayUserOrder() {
        orderDao = new OrdersDaoImpl();
        List<Order> orders = orderDao.getOrderData();
        List<Order> userOrders = new ArrayList<>();
        
        for (Order order : orders) {
            if (order.getUserId() == user.getId()) {
                userOrders.add(order);
            }
        }
        view.showMessage("Orders Details:");
        view.ordersTableUser(userOrders);
    }

    public void displayOrdersHistory() {
        orderHistoryDao = new OrdersHistoryDaoImpl();
        List<Order> ordersHistory = orderHistoryDao.getOrderHistoryDetails();
        List<Order> userOrders = new ArrayList<>();
        
        for (Order order : ordersHistory) {
            if (order.getUserId() == user.getId()) {
                userOrders.add(order);
            }
        }
        view.showMessage("Orders History:");
        view.ordersUserHistoryTable(userOrders);
    }

    public void handleAddProduct() {
        view.showMessage(view.ENTERPRODUCTID);
        int id = view.getIntegerInput();
        view.showMessage(view.ENTERAMOUNT);
        int amount = view.getIntegerInput();
        addProductToBasket(id, amount);
        productDao.deleteProductsByUser(String.valueOf(id), String.valueOf(amount));
    }

    public void handleDeleteProduct() {
        view.showMessage("Enter product ID you want to remove");
        int id = view.getIntegerInput();
        removeProductFromBasket(id);
    }

    public void placeOrderIfBasketNotEmpty() {
        OrdersDaoImpl orders = new OrdersDaoImpl();
        if (user.getBasket().checkIfBasketEmpty()) {
            view.showMessage("Add products to basket!");
            view.getEmptyInput();
        } else {
            orders.addOrder(user);
            user.getBasket().clearBasket();
        }
    }

    public void handleRateProduct() {
        view.showMessage("Enter product ID you want to rate");
        int id = view.getIntegerInput();
        view.showMessage("Enter your rating (1 - 5)");
        int userRating = view.getIntegerInput();
        while (shopIterator.hasNext()) {
            Product current = shopIterator.next();
            if (current.getId() == id) {
                current.setRating(current.getRating() + userRating);
                current.setNumberOfRatings(current.getNumberOfRatings() + 1);
                productDao.editProductRating(String.valueOf(id), String.valueOf(current.getRating()));
                productDao.editProductNumberOfRatings(String.valueOf(id), String.valueOf(current.getNumberOfRatings()));
            }
        }


    }
}