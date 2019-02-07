package com.codecool.onlineshop.service;

import java.util.List;

import com.codecool.onlineshop.dao.OrdersDaoImpl;
import com.codecool.onlineshop.dao.ProductDao;
import com.codecool.onlineshop.dao.ProductsDaoImpl;
import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.view.View;

public class AdminService {

    private View view;
    private ProductsDaoImpl productDao;
    private OrdersDaoImpl orderDao;

    public AdminService() {
        view = new View();
        productDao = new ProductsDaoImpl();
        orderDao = new OrdersDaoImpl();
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
        view.showMessage(view.DELETEPRODUCT);
        String productID = view.getStringInput();
        productDao.deleteProductAdmin(productID);;
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
        String getProductId = getID(view.ENTERPRODUCTID);
        String productName = editValueOfProduct(view.ENTERNAME);
        productDao.editProductName(getProductId, productName);
    }


    public void editProductPrice() {
        String getProductId = getID(view.ENTERPRODUCTID);
        String productPrice = editValueOfProduct(view.ENTERPRICE);
        productDao.editProductPrice(getProductId, productPrice);
    }

    public void  editProductAmount() {
        String getProductId = getID(view.ENTERPRODUCTID);
        String productAmount = editValueOfProduct(view.ENTERAMOUNT);
        productDao.editProductAmount(getProductId, productAmount);
    }

    public void displayAllProductsInShop() {
        ProductDao productDao = new ProductsDaoImpl();
        view.productsTable(productDao.getProducts());
    }

    public void displayOrdersForAdmin() {
        orderDao = new OrdersDaoImpl();
        List<Order> orders = orderDao.getOrderData();
        for (Order order : orders) {
            System.out.println(order);
        } 
    }
}