package com.effective.software.testing.effectivesoftwaretesting.ch7.domain;

import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.CustomerNotifier;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.DeliveryCenter;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.SAP;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.ShoppingCartRepository;

import java.time.LocalDate;
import java.util.List;

public class PaidShoppingCartsBatch {

    private ShoppingCartRepository db;
    private DeliveryCenter deliveryCenter;
    private CustomerNotifier notifier;
    private SAP sap;

    public PaidShoppingCartsBatch(
            ShoppingCartRepository db,
            DeliveryCenter deliveryCenter,
            CustomerNotifier notifier,
            SAP sap
    ) {
        this.db = db;
        this.deliveryCenter = deliveryCenter;
        this.notifier = notifier;
        this.sap = sap;
    }

    public void processAll() {
        List<ShoppingCart> paidShoppingCarts = db.cartsPaidToday();

        for (ShoppingCart cart : paidShoppingCarts) {
            LocalDate estimatedDayOfDelivery = deliveryCenter.deliver(cart); // 배송 시스템에 알린다.

            cart.markAsReadyForDelivery(estimatedDayOfDelivery); // 배송 준비를 완료 상태로 만들고 데이터베이스에 영속화한다.
            db.persist(cart);

            notifier.sendEstimatedDeliveryNotification(cart); // 고객에게 알림을 보낸다.

            sap.cartReadyForDelivery(cart); // SAP에 알린다.
        }
    }
}
