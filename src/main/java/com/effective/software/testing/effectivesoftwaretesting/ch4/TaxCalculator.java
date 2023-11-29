package com.effective.software.testing.effectivesoftwaretesting.ch4;

public class TaxCalculator {

    /**
     * 세금을 계산한다.
     * @param value 세금 계산을 위한 기본값. 값은 양수여야 한다.
     * @return 계산한 세금. 세금은 항상 양수여야 한다.
     */
    public double calculateTax(double value) {

        assert value >= 0 : "Value cannot be negative";

        double taxValue = 0;

        // 여기에 복잡한 비즈니스 규칙을 작성한다
        // 최종 계산 결과가 'taxValue'에 담겨 있다

        assert taxValue >= 0 : "Calculated tax value cannot be negative";

        return taxValue;
    }
}
