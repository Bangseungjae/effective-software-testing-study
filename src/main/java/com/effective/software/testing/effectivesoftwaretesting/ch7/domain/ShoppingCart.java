package com.effective.software.testing.effectivesoftwaretesting.ch7.domain;

import java.time.LocalDate;

public class ShoppingCart {

    private boolean readyForDelivery = false;
    private double value = 0;

    public ShoppingCart(double value) {
        this.value = value;
    }

    public ShoppingCart() {
    }
    // more info about the shopping cart...

    public void markAsReadyForDelivery(LocalDate estimatedDayOfDelivery) {
        this.readyForDelivery = true;
        // ...
    }

    public boolean isReadyForDelivery() {
        return readyForDelivery;
    }

    public double getValue() {
        return this.value;
    }
}
