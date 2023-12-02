package com.effective.software.testing.effectivesoftwaretesting.ch6.bookstore;

public interface BookRepository {

    Book findByISBN(String ISBN);
}
