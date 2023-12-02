package com.effective.software.testing.effectivesoftwaretesting.ch6.stub;

import java.util.List;

public class InvoiceFilterWithDatabase {

    public List<Invoice> lowValueInvoices() {
        DatabaseConnection connection = new DatabaseConnection();
        IssuedInvoices issuedInvoices = new IssuedInvoices(connection);

        try {
            List<Invoice> all = issuedInvoices.all();

            return all.stream()
                    .filter(invoice -> invoice.value() < 100)
                    .toList();
        } finally {
            connection.close();
        }
    }
}
