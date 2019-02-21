package com.codecool.onlineshop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void productToString() {
        Product product = new Product(1, "Water", "Soft Drink", 5, 10, 0, 0);
        assertEquals("id = 1, \nname = Water, \ncategory = Soft Drink, \nprice = 5, \namount = 10", product.toString());
    }

}