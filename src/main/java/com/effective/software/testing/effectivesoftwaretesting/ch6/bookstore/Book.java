package com.effective.software.testing.effectivesoftwaretesting.ch6.bookstore;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Book {

    private String ISBN;
    private int price;
    private int amount;

    public Book(String ISBN, int price, int amount) {
        this.ISBN = ISBN;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return price == book.price && amount == book.amount && Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, price, amount);
    }
}
