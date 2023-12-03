package com.effective.software.testing.effectivesoftwaretesting.ch7.ports;

import com.effective.software.testing.effectivesoftwaretesting.ch7.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartRepository {

    List<ShoppingCart> cartsPaidToday();
    void persist(ShoppingCart cart);
}
