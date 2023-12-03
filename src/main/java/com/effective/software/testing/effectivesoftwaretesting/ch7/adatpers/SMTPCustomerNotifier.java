package com.effective.software.testing.effectivesoftwaretesting.ch7.adatpers;

import com.effective.software.testing.effectivesoftwaretesting.ch7.domain.ShoppingCart;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.CustomerNotifier;

public class SMTPCustomerNotifier implements CustomerNotifier {

    @Override
    public void sendEstimatedDeliveryNotification(ShoppingCart cart) {
        // SMTP를 통해 이메일을 전송하는 코드
    }
}
