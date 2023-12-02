package com.effective.software.testing.effectivesoftwaretesting.ch6.exception;

public record SapInvoice(
        String customer,
        int value,
        String id
) {
    public SapInvoice(String customer, int value, String id) {
        assert customer != null;
        assert id != null;

        this.customer = customer;
        this.value = value;
        this.id = id;
    }
}
