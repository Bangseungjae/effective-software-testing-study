package com.effective.software.testing.effectivesoftwaretesting.ch6.christmas;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChristmasDiscountTest {

    private final Clock clock = mock(Clock.class);
    private final ChristmasDiscount cd = new ChristmasDiscount(clock);

    @Test
    void christmas() {
        LocalDate christmas = LocalDate.of(2015, Month.DECEMBER, 25);
        when(clock.now()).thenReturn(christmas);

        double finalValue = cd.applyDiscount(100.0);
        assertThat(finalValue).isCloseTo(85.0, offset(0.001));
    }


    @Test
    void notChristmas() {
        LocalDate notChristmas = LocalDate.of(2015, Month.DECEMBER, 26);
        when(clock.now()).thenReturn(notChristmas);

        double finalValue = cd.applyDiscount(100.0);
        assertThat(finalValue).isCloseTo(100.0, offset(0.001));
    }
}
