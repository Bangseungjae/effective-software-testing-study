package com.effective.software.testing.effectivesoftwaretesting.ch5.helper;

import com.effective.software.testing.effectivesoftwaretesting.ch5.Basket;
import com.effective.software.testing.effectivesoftwaretesting.ch5.Product;
import net.jqwik.api.stateful.Action;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class AddAction implements Action<Basket> {

    private final Product product;
    private final int qty;

    public AddAction(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    @Override
    public Basket run(Basket basket) {
        BigDecimal currentValue = basket.getTotalValue();
        basket.add(product, qty);

        BigDecimal newProductValue = product.getPrice().multiply(valueOf(qty));
        BigDecimal newValue = currentValue.add(newProductValue);
        assertThat(basket.getTotalValue()).isEqualByComparingTo(newValue);
        return basket;
    }

    @Override
    public String toString() {
        return "AddAction{" +
                "product=" + product +
                ", qty=" + qty +
                '}';
    }
}
