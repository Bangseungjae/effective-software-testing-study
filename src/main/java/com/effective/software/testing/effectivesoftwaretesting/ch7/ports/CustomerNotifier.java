package com.effective.software.testing.effectivesoftwaretesting.ch7.ports;

import com.effective.software.testing.effectivesoftwaretesting.ch7.domain.ShoppingCart;

public interface CustomerNotifier {

    void sendEstimatedDeliveryNotification(ShoppingCart cart);
}
