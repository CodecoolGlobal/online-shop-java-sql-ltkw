package com.codecool.onlineshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductIteratorTest {
    List<Product> fakeProductList = new ArrayList<>();
    //Iterator<Product> iteratorForTests;
    Product product = new Product.Builder().withId(1).withName("Water").withCategory("Soft Drink").withPrice(5).withAmount(10).build();


    @Test
    void nextTest() {
        fakeProductList.add(product);
        ProductIterator iterator = new ProductIterator(fakeProductList);
        assertEquals(product, iterator.next());
        fakeProductList.remove(product);
    }

    @Test
    void hasNextTest() {
        fakeProductList.add(product);
        ProductIterator iterator = new ProductIterator(fakeProductList);
        assertTrue(iterator.hasNext());
        fakeProductList.remove(product);
    }
//    @BeforeEach
//    public void addproductsToFakeProductList(){
//        fakeProductList.add(new Product(1,"name1","category2",1,1,0,0));
//        fakeProductList.add(new Product(2,"name1","category2",2,2,0,0));
//        fakeProductList.add(new Product(3,"name1","category2",3,3,0,0));
//        iteratorForTests = new ProductIterator(fakeProductList);
//    }
//
//
//
//    @Test
//    public void testIfIteratorPassesThroughListCorrectly_true(){
//        int numberOfIteratorPasses = 0;
//        while (iteratorForTests.hasNext()){
//            numberOfIteratorPasses += 1;
//        }
//        assertEquals(3, numberOfIteratorPasses);
//    }
//
//    @Test
//    public void testIfIteratorDontreturnsProductObject_false(){
//        int numberOfIteratorPasses = 0;
//        while (iteratorForTests.hasNext()) {
//            Product actuallproduct = iteratorForTests.next();
//            assertEquals(fakeProductList.get(numberOfIteratorPasses),actuallproduct);
//            numberOfIteratorPasses += 1;
//
//        }
//    }
}