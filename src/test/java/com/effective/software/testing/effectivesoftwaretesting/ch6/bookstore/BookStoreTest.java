package com.effective.software.testing.effectivesoftwaretesting.ch6.bookstore;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.*;

class BookStoreTest {

    @Test
    void moreComplexOrder() {
        BookRepository bookRepository = mock(BookRepository.class);
        BuyBookProcess process = mock(BuyBookProcess.class);

        Map<String, Integer> orderMap = new HashMap<>();

        orderMap.put("PRODUCT-ENOUGH-QTY", 5);
        orderMap.put("PRODUCT-PRECISE-QTY", 10);
        orderMap.put("PRODUCT-NOT-ENOUGH", 22);

        Book book1 = new Book("PRODUCT-ENOUGH-QTY", 20, 11); // 11 > 5
        when(bookRepository.findByISBN("PRODUCT-ENOUGH-QTY"))
                .thenReturn(book1);

        Book book2 = new Book("PRODUCT-PRECISE-QTY", 25, 10); // 10 == 10
        when(bookRepository.findByISBN("PRODUCT-PRECISE-QTY"))
                .thenReturn(book2);

        Book book3 = new Book("PRODUCT-NOT-ENOUGH", 37, 21); // 21 < 22
        when(bookRepository.findByISBN("PRODUCT-NOT-ENOUGH"))
                .thenReturn(book3);

        BookStore bookStore = new BookStore(bookRepository, process);
        Overview overview = bookStore.getPriceForCart(orderMap);

        int expectedPrice =
                5 * 20 +
                10 * 25 +
                21 * 37;

        assertThat(overview.getTotalPrice()).isEqualTo(expectedPrice);

        verify(process).buyBook(book1, 5);
        verify(process).buyBook(book2, 10);
        verify(process).buyBook(book3, 21);

        assertThat(overview.getUnavailable())
                .containsExactly(entry(book3, 1));
    }

}
