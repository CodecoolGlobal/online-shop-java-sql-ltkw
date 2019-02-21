package com.codecool.onlineshop.service;

import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    User user = new User(1, "Test", "test", "CUSTOMER");
    CustomerService customerService = new CustomerService(user);
    List<Product> products = new ArrayList<>();
    Product product = new Product(1, "Water", "Soft Drink", 5, 10, 0, 0);

    @Test
    void validateRatingTest() {

        assertEquals(5, customerService.validateRating());

    }

}