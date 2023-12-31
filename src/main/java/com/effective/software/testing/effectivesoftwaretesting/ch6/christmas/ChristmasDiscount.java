package com.effective.software.testing.effectivesoftwaretesting.ch6.christmas;

import java.time.LocalDate;
import java.time.Month;

public class ChristmasDiscount {

    private final Clock clock;

    public ChristmasDiscount(Clock clock) {
        this.clock = clock;
    }

    public double applyDiscount(double amount) {
        LocalDate today = clock.now();

        double discountPercentage = 0;
        boolean isChristmas = today.getMonth() == Month.DECEMBER && today.getDayOfMonth() == 25;

        if (isChristmas) {
            discountPercentage = 0.15;
        }

        return amount - (amount * discountPercentage);
    }
}
