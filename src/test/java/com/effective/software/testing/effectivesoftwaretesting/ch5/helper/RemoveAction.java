package com.effective.software.testing.effectivesoftwaretesting.ch5.helper;

import com.effective.software.testing.effectivesoftwaretesting.ch5.Basket;
import com.effective.software.testing.effectivesoftwaretesting.ch5.Product;
import net.jqwik.api.stateful.Action;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RemoveAction implements Action<Basket> {

    @Override
    public Basket run(Basket basket) {
        BigDecimal currentValue =  basket.getTotalValue();

        Set<Product> productsInBasket = basket.products();

        // if the basket is empty, simply skip this action
        if (productsInBasket.isEmpty()) {
            return basket;
        }

        // pick a random element in the basket to be removed
        Product randomProduct = pickRandom(productsInBasket);
        double currentProductQty = basket.quantityOf(randomProduct);

        basket.remove(randomProduct);

        BigDecimal basketValueWithoutRandomProduct = currentValue
                .subtract(randomProduct.getPrice().multiply(valueOf(currentProductQty)));

        assertThat(basket.getTotalValue())
                .isEqualByComparingTo(basketValueWithoutRandomProduct);
        return basket;
    }

    private Product pickRandom(Set<Product> set){

        Random random = new Random();
        int randomNumber = random.nextInt(set.size());

        int currentIndex = 0;
        Product randomElement = null;

        for(Product element : set){
            randomElement = element;

            if(currentIndex == randomNumber)
                return randomElement;

            currentIndex++;
        }

        return randomElement;
    }

    @Override
    public String toString() {
        return "RemoveAction";
    }
}
