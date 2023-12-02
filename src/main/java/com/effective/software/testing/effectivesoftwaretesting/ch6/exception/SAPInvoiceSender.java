package com.effective.software.testing.effectivesoftwaretesting.ch6.exception;

import com.effective.software.testing.effectivesoftwaretesting.ch6.stub.Invoice;
import com.effective.software.testing.effectivesoftwaretesting.ch6.stub.InvoiceFilter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SAPInvoiceSender {

    private final InvoiceFilter filter;
    private final SAP sap;

    public SAPInvoiceSender(InvoiceFilter filter, SAP sap) {
        this.filter = filter;
        this.sap = sap;
    }

    public List<Invoice> sendLowValuedInvoices() {
        List<Invoice> failedInvoices = new ArrayList<>();

        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();

        for (Invoice invoice : lowValuedInvoices) {
            String customer = invoice.customer();
            int value = invoice.value();
            String sapId = generateId(invoice);
            SapInvoice sapInvoice = new SapInvoice(customer, value, sapId);

            try {
                sap.send(sapInvoice);
            } catch (SAPException e) {
                failedInvoices.add(invoice);
            }
        }
        return failedInvoices;
    }

    private String generateId(Invoice invoice) {
        String date = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String customer = invoice.customer();

        return date + (customer.length() >= 2 ? customer.substring(0, 2) : "X");
    }
}
