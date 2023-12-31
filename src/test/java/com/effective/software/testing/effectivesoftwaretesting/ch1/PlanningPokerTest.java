package com.effective.software.testing.effectivesoftwaretesting.ch1;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.IntegerArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlanningPokerTest {

    @Test
    void rejectNullInput() {
        assertThatThrownBy(() -> new PlanningPoker().identifyExtremes(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectEmptyInput() {
        assertThatThrownBy(() -> new PlanningPoker().identifyExtremes(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectSingleInput() {
        List<Estimate> list = Arrays.asList(new Estimate("Eleanor", 1));
        assertThatThrownBy(() -> new PlanningPoker().identifyExtremes(list))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void twoEstimates() throws IllegalAccessException {
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Frank", 5)
        );
        List<String> devs = new PlanningPoker().identifyExtremes(list);
        assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Frank");
    }

    @Test
    void manyEstimates() throws IllegalAccessException {
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Arie", 5),
                new Estimate("Frank", 7)
        );

        List<String> devs = new PlanningPoker().identifyExtremes(list);
        assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Arie");
    }

    @Property
    void inAnyOrder(@ForAll("estimates") List<Estimate> estimates) throws IllegalAccessException {
        estimates.add(new Estimate("MrLowEstimate", 1));
        estimates.add(new Estimate("MsHighEstimate", 100));

        Collections.shuffle(estimates);

        List<String> dev = new PlanningPoker().identifyExtremes(estimates);

        assertThat(dev)
                .containsExactlyInAnyOrder("MrLowEstimate", "MsHighEstimate");
    }

    @Test
    void developerWithSameEstimates() throws IllegalAccessException {
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Arie", 5),
                new Estimate("Andy", 10),
                new Estimate("Frank", 7),
                new Estimate("Annibale", 5)
        );
        List<String> devs = new PlanningPoker().identifyExtremes(list);
        assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Arie");
    }

    @Test
    void allDevelopersWithTheSameEstimate() throws IllegalAccessException {
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Arie", 10),
                new Estimate("Andy", 10),
                new Estimate("Frank", 10),
                new Estimate("Annibale",10)
        );
        List<String> devs = new PlanningPoker().identifyExtremes(list);
        assertThat(devs).isEmpty();
    }

    @Provide
    Arbitrary<List<Estimate>> estimates() {
        StringArbitrary names = Arbitraries.strings()
                .withCharRange('a', 'z').ofLength(5);
        IntegerArbitrary values = Arbitraries.integers().between(2, 99);
        Arbitrary<Estimate> estimates = Combinators.combine(names, values)
                .as((name, value) -> new Estimate(name, value));
        return estimates.list().ofMinSize(1);

    }
}
