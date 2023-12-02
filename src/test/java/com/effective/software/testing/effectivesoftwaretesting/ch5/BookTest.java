package com.effective.software.testing.effectivesoftwaretesting.ch5;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.IntegerArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Property
    void differentBooks(
            @ForAll("books")
            Book book
    ) {
        System.out.println(book);
    }

    @Provide
    Arbitrary<Book> books() {
        StringArbitrary titles = Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(10).ofMaxLength(100);
        StringArbitrary authors = Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(5).ofMaxLength(21);
        IntegerArbitrary qtyOfPages = Arbitraries.integers().between(0, 450);

        return Combinators.combine(titles, authors, qtyOfPages)
                .as((title, author, pages) -> new Book(title, author, pages));
    }

}
