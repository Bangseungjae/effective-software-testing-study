package com.effective.software.testing.effectivesoftwaretesting.ch6.mock;

import com.effective.software.testing.effectivesoftwaretesting.ch6.stub.Invoice;
import com.effective.software.testing.effectivesoftwaretesting.ch6.stub.InvoiceFilter;

import java.util.List;

public class SAPInvoiceSender {

    private final InvoiceFilter filter;
    private final SAP sap;

    public SAPInvoiceSender(InvoiceFilter invoiceFilter, SAP sap) {
        this.filter = invoiceFilter;
        this.sap = sap;
    }

    public void sendLowValuedInvoices() {
        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();
        for (Invoice invoice : lowValuedInvoices) {
            sap.send(invoice);
        }
    }
}
