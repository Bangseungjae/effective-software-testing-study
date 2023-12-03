package com.effective.software.testing.effectivesoftwaretesting.ch7.adatpers;

import com.effective.software.testing.effectivesoftwaretesting.ch7.domain.ShoppingCart;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.DeliveryCenter;

import java.time.LocalDate;

public class DeliveryCenterRestApi implements DeliveryCenter {

    @Override
    public LocalDate deliver(ShoppingCart cart) {
        // 배송 API를 이용하여 통신하는 코드
        // LocalDate를 반환한다.
        return null;
    }
}
