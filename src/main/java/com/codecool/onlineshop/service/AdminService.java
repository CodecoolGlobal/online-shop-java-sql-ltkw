package com.codecool.onlineshop.service;

import java.util.List;

import com.codecool.onlineshop.dao.*;
import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.OrderStatus;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.View;

public class AdminService {

    private View view;
    private ProductsDaoImpl productDao;
    private OrdersDaoImpl orderDao;
    private OrdersHistoryDaoImpl orderHistoryDao;
    private UsersDaoImpl usersDao;
    private boolean wrongInput;

    public AdminService() {
        view = new View();
        productDao = new ProductsDaoImpl();
        orderDao = new OrdersDaoImpl();
        orderHistoryDao = new OrdersHistoryDaoImpl();
        usersDao = new UsersDaoImpl();
    }
    
    public void createNewProduct() {
        view.showMessage(view.ENTERPRODUCT);
        String name = view.getStringInput();
        view.showMessage(view.ENTERCATEGORY);
        String category = view.getStringInput();
        view.showMessage(view.ENTERPRICE);
        String price = view.getStringInput();
        view.showMessage(view.ENTERAMOUNT);
        String amount = view.getStringInput();
        productDao.addNewProduct(name, category, price, amount);
    }

    public void deleteProductAdmin() {
        boolean editProduct = true;
        while (editProduct) {
            String productID = getID(view.DELETEPRODUCT);
            if (productDao.validID(productID)) {
                productDao.deleteProductAdmin(productID);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    public String getID(String message) {
        view.showMessage(message);
        String productID = view.getStringInput();
        return productID;
    }

    public String editValueOfProduct(String message) {
        view.showMessage(message);
        String producValue = view.getStringInput();
        return producValue;
    }

    public void editProductName() {
        boolean editProduct = true;
        while(editProduct) {
            String productId = getID(view.ENTERPRODUCTID);
            if (productDao.validID(productId)) {
                String productName = editValueOfProduct(view.ENTERNAME);
                productDao.editProductName(productId, productName);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    public void editProductPrice() {
        String columnName = "Price";
        boolean editProduct = true;
        while (editProduct) {
            String productId = getID(view.ENTERPRODUCTID);
            if (productDao.validID(productId)) {
                String productPrice = editValueOfProduct(view.ENTERPRICE);
                productDao.editProductPrice(productId, productPrice, columnName);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }


    public void  editProductAmount() {
        String columnName = "Amount";
        boolean editProduct = true;
        while (editProduct) {
            String productId = getID(view.ENTERPRODUCTID);
            if (productDao.validID(productId)) {
                String productAmount = editValueOfProduct(view.ENTERAMOUNT);
                productDao.editProductAmount(productId, productAmount, columnName);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    public void displayAllProductsInShop() {
        ProductDao productDao = new ProductsDaoImpl();
        view.productsTable(productDao.getProducts());
    }

    public void displayAllOrders() {
        orderDao = new OrdersDaoImpl();
        List<Order> orders = orderDao.getOrderData();
        view.showMessage("Orders Details:");
        view.ordersTableAdmin(orders);
    }

    public void displayOrdersHistory() {
        orderHistoryDao = new OrdersHistoryDaoImpl();
        List<Order> ordersHistory = orderHistoryDao.getOrderHistoryDetails();
        view.showMessage("Orders History:");
        view.ordersHistoryTable(ordersHistory);
    }

    public void displayAllCustomers(){
        usersDao = new UsersDaoImpl();
        List<User> allUsers = usersDao.getUserData();
        view.showMessage("Table of all customers");
        view.usersTable(allUsers);
    }

    public void deleteUser() {
        usersDao = new UsersDaoImpl();
        view.showMessage(view.ENTERUSERID);
        int userID = view.getIntegerInput();
        usersDao.deleteUser(userID);
        view.showMessage("Successfully Deleted");
    }

    public void updateUserDetails() {
        usersDao = new UsersDaoImpl();
        wrongInput = true;
        view.showMessage(view.ENTERUSERID);
        int userID = view.getIntegerInput();
        if (usersDao.isValid(userID)) {
            view.showMessage(view.ENTERNEWVALUE);
            String newValue = view.getStringInput();
            view.displayUpdateUserDetailsMenu();
            while (wrongInput) {
                handleUpdateUserDetails(userID, newValue);
            }
            view.showMessage(view.UPDATED);
        } else {
            view.showMessage(view.NOOPTION);
        }
    }

    public void updateOrderStatus() {
        orderDao = new OrdersDaoImpl();
        wrongInput = true;
        view.showMessage("Enter ID of an Order: ");
        int orderId = view.getIntegerInput();
        if (orderDao.isValid(orderId)) {
            view.displayUpdateStatusMenu();
            while (wrongInput) {
                handleOrderStatusUpdate(orderId);
            }
            view.showMessage(view.UPDATED);
        } else {
            view.showMessage(view.NOOPTION);
        }
    }

    private void handleOrderStatusUpdate(int orderId) {
        int chosenOption = view.getIntegerInput();
        switch (chosenOption) {
            case 1:
                orderDao.updateOrderStatus(orderId, OrderStatus.PENDING.name());
                wrongInput = false;
                break;
            case 2:
                orderDao.updateOrderStatus(orderId, OrderStatus.SENT.name());
                wrongInput = false;
                break;
            case 3:
                orderDao.updateOrderStatus(orderId, OrderStatus.DELIVERED.name());
                wrongInput = false;
                break;
            case 4:
                orderDao.updateOrderStatus(orderId, OrderStatus.CANCELED.name());
                wrongInput = false;
                break;
            case 0:
                wrongInput = false;
                break;
            default:
                view.showMessage(view.NOOPTION);
                break;
        }
    }

    private void handleUpdateUserDetails(int userID, String newValue) {
        int chosenOption = view.getIntegerInput();
        switch (chosenOption) {
            case 1:
                usersDao.updateUser(userID, "Name", newValue);
                wrongInput = false;
                break;
            case 2:
                usersDao.updateUser(userID, "Password", newValue);
                wrongInput = false;
                break;
            case 3:
                usersDao.updateUser(userID, "UserType", newValue);
                wrongInput = false;
                break;
            case 0:
                view.showMessage(view.NOOPTION);
                break;
        }
    }
}