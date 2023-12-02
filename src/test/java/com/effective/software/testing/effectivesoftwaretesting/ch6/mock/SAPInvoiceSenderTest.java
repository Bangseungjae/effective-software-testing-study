package com.effective.software.testing.effectivesoftwaretesting.ch6.mock;

import com.effective.software.testing.effectivesoftwaretesting.ch6.stub.Invoice;
import com.effective.software.testing.effectivesoftwaretesting.ch6.stub.InvoiceFilter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SAPInvoiceSenderTest {

    private InvoiceFilter filter = mock(InvoiceFilter.class);
    private SAP sap = mock(SAP.class);

    private SAPInvoiceSender sender = new SAPInvoiceSender(filter, sap);

    /**
     * 모의 객체와 기댓값
     */
    @Test
    void sendToSap() {

        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 99);

        List<Invoice> invoices = Arrays.asList(mauricio, frank);

        when(filter.lowValueInvoices()).thenReturn(invoices);
        sender.sendLowValuedInvoices();

        verify(sap, times(2)).send(any(Invoice.class));
        verify(sap, times(1)).send(mauricio);
        verify(sap, times(1)).send(frank);
    }

    @Test
    void noLowValueInvoices() {
        List<Invoice> invoices = emptyList();
        when(filter.lowValueInvoices()).thenReturn(invoices);

        sender.sendLowValuedInvoices();

        verify(sap, never()).send(any(Invoice.class));
    }

}
