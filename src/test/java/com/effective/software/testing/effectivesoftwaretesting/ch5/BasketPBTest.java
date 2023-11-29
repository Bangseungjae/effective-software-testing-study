package com.effective.software.testing.effectivesoftwaretesting.ch5;

import com.effective.software.testing.effectivesoftwaretesting.ch5.helper.AddAction;
import com.effective.software.testing.effectivesoftwaretesting.ch5.helper.RemoveAction;
import net.jqwik.api.*;
import net.jqwik.api.stateful.ActionSequence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasketPBTest {

    @Provide
    Arbitrary<ActionSequence<Basket>> addAndRemoves() {
        return Arbitraries.sequences(Arbitraries.oneOf(
                addAction(),
                removeAction()
        ));
    }

    @Property(afterFailure = AfterFailureMode.SAMPLE_ONLY)
    void sequenceOfAddsAndRemoves(
            @ForAll("addAndRemoves")
            ActionSequence<Basket> actions
    ) {
        actions.run(new Basket());
    }

    static List<Product> randomProducts = new ArrayList<>() {{
        add(new Product("TV", new BigDecimal("100")));
        add(new Product("Playstation", new BigDecimal("150.3")));
        add(new Product("Refrigerator", new BigDecimal("180.27")));
        add(new Product("Soda", new BigDecimal("2.69")));
    }};

    private Arbitrary<AddAction> addAction() {
        Arbitrary<Product> products = Arbitraries.oneOf(
                randomProducts.stream()
                        .map(product -> Arbitraries.of(product))
                        .collect(Collectors.toList()));

        Arbitrary<Integer> qtys = Arbitraries.integers().between(1, 100);

        return Combinators
                .combine(products, qtys)
                .as((product, qty) -> new AddAction(product, qty));
    }

    private Arbitrary<RemoveAction> removeAction() {
        return Arbitraries.of(new RemoveAction());
    }
}
