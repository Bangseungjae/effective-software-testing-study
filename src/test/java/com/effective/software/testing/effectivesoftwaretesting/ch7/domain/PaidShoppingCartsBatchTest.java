package com.effective.software.testing.effectivesoftwaretesting.ch7.domain;

import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.CustomerNotifier;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.DeliveryCenter;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.SAP;
import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaidShoppingCartsBatchTest {

    @Mock
    private ShoppingCartRepository db;
    @Mock
    private DeliveryCenter deliveryCenter;
    @Mock
    private CustomerNotifier notifier;
    @Mock
    private SAP sap;

    @Test
    void theWholeProcessHappens() {
        PaidShoppingCartsBatch batch = new PaidShoppingCartsBatch(
                db,
                deliveryCenter,
                notifier,
                sap
        );

        ShoppingCart someCart = new ShoppingCart();
        LocalDate someDate = LocalDate.now();

        when(db.cartsPaidToday()).thenReturn(List.of(someCart));
        when(deliveryCenter.deliver(someCart)).thenReturn(someDate);

        batch.processAll();

        verify(deliveryCenter).deliver(someCart);
        verify(notifier).sendEstimatedDeliveryNotification(someCart);
        verify(db).persist(someCart);
        verify(sap).cartReadyForDelivery(someCart);

        assertThat(someCart.isReadyForDelivery()).isTrue();
    }
}
