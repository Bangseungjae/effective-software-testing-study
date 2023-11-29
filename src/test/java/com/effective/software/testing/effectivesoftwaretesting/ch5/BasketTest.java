package com.effective.software.testing.effectivesoftwaretesting.ch5;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    private Basket basket = new Basket();

    /**
     * 장바구니에 물건을 추가하는 테스트
     */
    @Test
    void addProducts() {
        basket.add(new Product("TV", valueOf(10)), 2);
        basket.add(new Product("Playstation", valueOf(100)), 1);

        assertThat(basket.getTotalValue())
                .isEqualByComparingTo(valueOf(10*2 + 100*1));
    }

    /**
     * 같은 물건을 두 번을 추가하면 수량만큼 합산한다
     */
    @Test
    void addSameProductTwice() {
        Product p = new Product("TV", valueOf(10));
        basket.add(p, 2);
        basket.add(p, 3);

        assertThat(basket.getTotalValue())
                .isEqualTo(valueOf(10 * 5));
    }

    /**
     * 장바구니에서 물건을 제거하는 테스트
     */
    @Test
    void removeProducts() {
        basket.add(new Product("TV", valueOf(100)), 1);

        Product p = new Product("Playstation", valueOf(10));
        basket.add(p, 2);
        basket.remove(p);

        assertThat(basket.getTotalValue())
                .isEqualByComparingTo(valueOf(100)); // 이 단언문 가지고 충분할까?
    }

}
