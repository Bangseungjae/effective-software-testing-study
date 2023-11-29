package com.effective.software.testing.effectivesoftwaretesting.ch4;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private BigDecimal totalValue = BigDecimal.ZERO;
    private Map<Product, Integer> basket = new HashMap<>();

    /**
     *
     * @param product null 이면 안된다.
     * @param qtyToAdd 0보다 커야한다
     * 사후 조건: 합계가 이전 합계보다 크다는 것을 보장한다
     * 불변식: 장바구니 제품의 합계는 양수다
     */
    public void add(Product product, int qtyToAdd) {
        assert product != null : "Product is required";
        assert qtyToAdd > 0 : "Quantity has to be greater than zero";
        BigDecimal oldTotalValue = totalValue;


        // ...
        // 제품을 추가한다
        // 합계를 갱신한다

        assert basket.containsKey(product) : "Product was not inserted in the basket";
        assert totalValue.compareTo(oldTotalValue) > 0 : "Total value should be greater than previous total value";
        assert invariant() : "Total value can't be negative";
    }

    /**
     *
     * @param product null이면 안된다, 장바구니에 있어야 한다
     * 사후 조건: 제품이 더 이상 장바구니에 있으면 안 된다.
     * 불변식: 장바구니 제품의 합계는 양수다
     */
    public void remove(Product product) {
        assert product != null : "product can't be null";
        assert basket.containsKey(product) : "Product must already be in the basket";

        // 장바구니에서 제품을 뺀다
        // 합계를 갱신한다

        assert !basket.containsKey(product) : "Product is still in the basket";
        assert invariant() : "Total value can't be negative";
    }

    private boolean invariant() {
        return totalValue.compareTo(BigDecimal.ZERO) >= 0;
    }

    class Product {
        private String name;
        private BigDecimal price;
    }

}
