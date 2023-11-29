package com.effective.software.testing.effectivesoftwaretesting.ch2;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> items = new ArrayList<>();

    public void add(CartItem item) {
        this.items.add(item);
    }

    public double totalPrice() {
        double totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.unitPrice() * item.quantity();
        }
        return totalPrice;
    }
}
