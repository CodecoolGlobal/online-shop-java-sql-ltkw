package com.codecool.onlineshop.service;

import java.util.List;

import com.codecool.onlineshop.dao.*;
import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.OrderStatus;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.model.UserType;
import com.codecool.onlineshop.view.View;

public class AdminService {

    private View view;
    private ProductsDaoImpl productDao;
    private OrdersDaoImpl orderDao;
    private OrdersHistoryDaoImpl orderHistoryDao;
    private UsersDaoImpl usersDao;
    private boolean wrongInput;
    private final String COLUMNAMOUNT = "Amount";
    private final String COLUMNPRICE = "Price";
    

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
        int price = view.getIntegerInput();
        view.showMessage(view.ENTERAMOUNT);
        int amount = view.getIntegerInput();
        productDao.addNewProduct(name, category, price, amount);
        productDao = new ProductsDaoImpl();
    }

    public void deleteProductAdmin() {
        boolean editProduct = true;
        while (editProduct) {
            int productID = getID(view.DELETEPRODUCT);
            if (productDao.validID(productID)) {
                productDao.deleteProductAdmin(productID);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    public Integer getID(String message) {
        view.showMessage(message);
        int productID = view.getIntegerInput();
        return productID;
    }

    public Integer editValueOfProduct(String message) {
        view.showMessage(message);
        int producValue = view.getIntegerInput();
        return producValue;
    }

    public String editNameOfProduct(String message) {
        view.showMessage(message);
        String producValue = view.getStringInput();
        return producValue;
    }

    public void editProductName() {
        boolean editProduct = true;
        while(editProduct) {
            int productId = getID(view.ENTERPRODUCTID);
            if (productDao.validID(productId)) {
                String productName = editNameOfProduct(view.ENTERNAME);
                productDao.editProductName(productId, productName);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    private void editProductValue(String column, String sentence) {
        boolean editProduct = true;
        while(editProduct) {
            int productId = getID(view.ENTERPRODUCTID);
            if (productDao.validID(productId)) {
                int productValue = editValueOfProduct(sentence);
                productDao.editProduct(productId, productValue, column);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    public void editProductPrice() {
        editProductValue(COLUMNPRICE, view.ENTERPRICE);
    }

    public void  editProductAmount() {
        editProductValue(COLUMNAMOUNT, view.ENTERAMOUNT);
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
        if (usersDao.isValid(userID)) {
            usersDao.deleteUser(userID);
            view.showMessage("Successfully Deleted");
        } else {
            view.showMessage(view.NOOPTION);
        }
    }

    public void updateUserDetails() {
        usersDao = new UsersDaoImpl();
        wrongInput = true;
        view.showMessage(view.ENTERUSERID);
        int userID = view.getIntegerInput();
        if (usersDao.isValid(userID)) {
            view.displayUpdateUserDetailsMenu();
            while (wrongInput) {
                handleUpdateUserDetails(userID);
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

    private void handleUpdateUserDetails(int userID) {
        String newValue;
        int chosenOption = view.getIntegerInput();
        switch (chosenOption) {
            case 1:
                newValue = getNewValueFromUser();
                usersDao.updateUser(userID, "Name", newValue);
                wrongInput = false;
                break;
            case 2:
                newValue = getNewValueFromUser();
                usersDao.updateUser(userID, "Password", newValue);
                wrongInput = false;
                break;
            case 3:
                newValue = handleUserTypeUpdate();
                usersDao.updateUser(userID, "UserType", newValue);
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

    private String handleUserTypeUpdate() {
        wrongInput = true;
        while (wrongInput) {
            view.showMessage("\n1. Customer\n2. Admin");
            int chosenOption = view.getIntegerInput();
            switch (chosenOption) {
                case 1:
                    return UserType.CUSTOMER.name();
                case 2:
                    return UserType.ADMIN.name();
                default:
                    view.showMessage(view.NOOPTION);
                    break;
            }
        }
        return null;
    }

    private String getNewValueFromUser() {
        view.showMessage(view.ENTERNEWVALUE);
        return view.getStringInput();
    }
}