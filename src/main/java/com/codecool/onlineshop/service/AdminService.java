package com.codecool.onlineshop.service;

import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.dao.ProductDao;
import com.codecool.onlineshop.dao.ProductsDaoImpl;
import com.codecool.onlineshop.view.View;

public class AdminService {

    //private User user;
    private View view;
    private ProductsDaoImpl productDao;

    public AdminService() {//User user) {
        //this.user = user;
        view = new View();
        productDao = new ProductsDaoImpl();
    }

    // public User getUser() {
    //     return this.user;
    // }
 
    
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

    public void deleteProductByUser() {
        view.showMessage(view.ENTERPRODUCTID);
        String productID = view.getStringInput();
        view.showMessage(view.ENTERAMOUNT);
        String productAmount = view.getStringInput();
        productDao.deleteProductsByUser(productID, productAmount);
    }

    public void editProductName(){
        view.showMessage(view.ENTERPRODUCTID);
        String productID = view.getStringInput();
        view.showMessage(view.ENTERNAME);
        String productName = view.getStringInput();
        productDao.editProductName(productID, productName);
    }


    public void editProductPrice() {
        view.showMessage(view.ENTERPRODUCTID);
        String productID = view.getStringInput();
        view.showMessage(view.ENTERPRICE);
        String productPrice = view.getStringInput();
        productDao.editProductPrice(productID, productPrice);
    }

    public void displayAllProductsInShop() {
        ProductDao productDao = new ProductsDaoImpl();
        view.productsTable(productDao.getProducts());
    }
}