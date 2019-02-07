package com.codecool.onlineshop.main;

import java.util.ArrayList;

import com.codecool.onlineshop.controller.CustomerController;
import com.codecool.onlineshop.controller.LoginController;
import com.codecool.onlineshop.dao.ProductsDaoImpl;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.ProductIterator;

public class Main {
    public static void main(String[] args) {
        CustomerController controller = new CustomerController();
        controller.handleCustomerController();

        // ProductsDaoImpl product = new ProductsDaoImpl();
        // ArrayList<Product> prod = (ArrayList<Product>) product.getProducts();
        // ProductIterator iter = new ProductIterator(prod);
        // while (iter.hasNext()) {
        //     System.out.println(iter.next());
        // }

        //product.getProductData();

        // String name = "Mango";
        // String category = "Juice";
        // int price = 6;
        // int amount = 21;
        // product.addNewProduct(name, category, price, amount);
    }
}