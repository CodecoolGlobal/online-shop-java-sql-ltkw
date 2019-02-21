package com.codecool.onlineshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codecool.onlineshop.dao.OrdersDaoImpl;
import com.codecool.onlineshop.dao.OrdersHistoryDaoImpl;
import com.codecool.onlineshop.dao.ProductsDaoImpl;
import com.codecool.onlineshop.dao.UsersDaoImpl;

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

    public void displayProductsByCategory() {
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
        List<Order> userOrders = getOrderDataForUser(orders);
        view.showMessage("Orders Details:");
        view.ordersTableUser(userOrders);
    }

    public void displayOrdersHistory() {
        orderHistoryDao = new OrdersHistoryDaoImpl();
        List<Order> ordersHistory = orderHistoryDao.getOrderHistoryDetails();
        List<Order> userOrders = getOrderDataForUser(ordersHistory);
        view.showMessage("Orders History:");
        view.ordersUserHistoryTable(userOrders);
    }


    private Integer getProductId() {
        view.showMessage(view.ID);
        int id = view.getIntegerInput();
        return id;
    }

    private Integer getProductAmount() {
        view.showMessage(view.ENTERAMOUNT);
        int amount = view.getIntegerInput();
        return amount;
    }

    private boolean validAmount(int id, int amount) {
        if (productDao.productAmountIsValid(id, amount)) {
            addProductToBasket(id, amount);
            productDao.deleteProductsByUser(id, amount);
            return true;
        } else {
            view.showMessage(view.WRONGAMOUNT);
            return false;
        }
    }

    public void handleAddProduct() {
        boolean editProduct = true;
        while (editProduct) {
            int id = getProductId();
            if (productDao.validID(id)) {
                int amount = getProductAmount();
                if (validAmount(id, amount)) {
                    editProduct = false;
                }
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    private void addProductToShop(int id) {
        while (shopIterator.hasNext()) {
            Product current = shopIterator.next();
            if (current.getId() == id) {
                productDao.addProductByUser(id, current.getAmount());
            }
        }
    }

    public void handleDeleteProduct() {
        view.showMessage("Enter product ID you want to remove");
        int id = view.getIntegerInput();
        while (!productDao.validID(id)) {
            id = view.getIntegerInput();
        }
        addProductToShop(id);
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
        String columnName = "RatingsAmount";
        String columnName1 = "Rating";
        view.showMessage("Enter product ID you want to rate");
        int id = view.getIntegerInput();
        while (!productDao.validID(id)) {
            id = view.getIntegerInput();
        }
        view.showMessage("Enter your rating (1 - 5)");
        int userRating = validateRating();
        while (shopIterator.hasNext()) {
            Product current = shopIterator.next();
            if (current.getId() == id) {
                current.setRating(current.getRating() + userRating);
                current.setNumberOfRatings(current.getNumberOfRatings() + 1);
                productDao.editProductRating(id, current.getRating(), columnName1);
                productDao.editProductNumberOfRatings(id, current.getNumberOfRatings(), columnName);
            }
        }
    }

    public void changePassword() {
        UsersDaoImpl usersDao = new UsersDaoImpl();
        view.showMessage(view.ENTERNEWVALUE);
        String newPassword = view.getStringInput();
        usersDao.updateUser(user.getId(), "Password", newPassword);
        view.showMessage("Successfully Updated");
    }

    private List<Order> getOrderDataForUser(List<Order> allOrders) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getUserId() == user.getId()) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public int validateRating() {
        int userRating = view.getIntegerInput();
        while (!String.valueOf(userRating).matches("[1-5]")) {
            view.showMessage("Enter number from 1 to 5!");
            userRating = view.getIntegerInput();
        }
        return userRating;
    }
}