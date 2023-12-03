package com.effective.software.testing.effectivesoftwaretesting.ch7.ports;

import com.effective.software.testing.effectivesoftwaretesting.ch7.domain.ShoppingCart;

import java.time.LocalDate;

public interface DeliveryCenter {

    LocalDate deliver(ShoppingCart cart);
}
