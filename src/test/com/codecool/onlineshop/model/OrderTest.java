package com.codecool.onlineshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void compareToReturnsZeroIfBothOrdersAreEqualTest() {
        Order firstOrder = new Order.Builder().withOrderId(3).build();
        Order secondOrder = new Order.Builder().withOrderId(3).build();
        assertEquals(0, firstOrder.compareTo(secondOrder));
    }

    @Test
    void compareToReturnsNegativeValueIfFirstOrderIsLessThanSecondOrderTest() {
        Order firstOrder = new Order.Builder().withOrderId(1).build();
        Order secondOrder = new Order.Builder().withOrderId(3).build();
        assertEquals(-1, firstOrder.compareTo(secondOrder));
    }

    @Test
    void compareToReturnsOneIfFirstOrderIsGreaterThanSecondOrderTest() {
        Order firstOrder = new Order.Builder().withOrderId(7).build();
        Order secondOrder = new Order.Builder().withOrderId(4).build();
        assertEquals(1, firstOrder.compareTo(secondOrder));
    }

    @Test
    void compareToThrowsNullPointerExceptionWhenComparingDifferentTypes() {
        Throwable e = null;
        Order testOrderString = new Order.Builder().withStatus("Test").build();
        Order testOrderInt = new Order.Builder().withProductId(4).build();
        try {
            assertEquals(1, testOrderString.compareTo(testOrderInt));
        } catch (Throwable exception) {
            e = exception;
        }
        assertTrue(e instanceof NullPointerException);
    }
}