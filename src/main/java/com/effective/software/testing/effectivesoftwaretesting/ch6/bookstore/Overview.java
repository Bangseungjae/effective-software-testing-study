package com.effective.software.testing.effectivesoftwaretesting.ch6.bookstore;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Overview {

    private int totalPrice;
    private Map<Book, Integer> unavailable;

    public Overview() {
        this.totalPrice = 0;
        this.unavailable = new HashMap<>();
    }

    public void addUnavailable(Book book, int unavailableQty) {
        this.unavailable.put(book, unavailableQty);
    }

    public void addToTotalPrice(int valueToAdd) {
        totalPrice += valueToAdd;
    }
}
