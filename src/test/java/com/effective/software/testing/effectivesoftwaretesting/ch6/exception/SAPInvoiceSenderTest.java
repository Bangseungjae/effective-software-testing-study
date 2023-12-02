package com.effective.software.testing.effectivesoftwaretesting.ch6.exception;

import com.effective.software.testing.effectivesoftwaretesting.ch6.stub.Invoice;
import com.effective.software.testing.effectivesoftwaretesting.ch6.stub.InvoiceFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.mockito.Mockito.*;

class SAPInvoiceSenderTest {

    private InvoiceFilter filter = mock(InvoiceFilter.class);
    private SAP sap = mock(SAP.class);

    private SAPInvoiceSender sender = new SAPInvoiceSender(filter, sap);

    @Test
    void sendSapInvoiceToSap() {
        Invoice mauricio = new Invoice("Mauricio", 20);

        List<Invoice> invoices = Arrays.asList(mauricio);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        sender.sendLowValuedInvoices();

        verify(sap).send(any(SapInvoice.class));
    }

    @ParameterizedTest
    @CsvSource({
            "Mauricio,Ma",
            "M,X"
    })
    void sendToSapWithTheGeneratedId(String customer, String customerCode) {
        Invoice mauricio = new Invoice(customer, 20);

        List<Invoice> invoices = List.of(mauricio);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        sender.sendLowValuedInvoices();

        // ArgumentCaptor 인스턴스를 포획하길 바라는 객체 타입으로 생성한다.
        ArgumentCaptor<SapInvoice> captor = ArgumentCaptor.forClass(SapInvoice.class);

        // verify 메서드를 호출하고 메서드의 매배견수에 인수 포획기를 전달한다.
        verify(sap).send(captor.capture());

        // 인수가 이미 포획되었다. 이제 값을 추출한다.
        SapInvoice generatedSapInvoice = captor.getValue();

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        assertThat(generatedSapInvoice).isEqualTo(new SapInvoice(customer, 20, date + customerCode));
    }

    @Test
    void returnFailedInvoices() {
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 25);
        Invoice steve = new Invoice("Steve", 48);

        List<Invoice> invoices = Arrays.asList(mauricio, frank, steve);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        String date = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMddyyyy"));
        SapInvoice franksInvoice = new SapInvoice("Frank", 25, date + "Fr");
        doThrow(new SAPException()).when(sap).send(franksInvoice);

        List<Invoice> failedInvoices = sender.sendLowValuedInvoices();
        assertThat(failedInvoices).containsExactly(frank);

        SapInvoice mauricioInvoice = new SapInvoice("Mauricio", 20, date + "Ma");
        verify(sap).send(mauricioInvoice);

        SapInvoice steveInvoice = new SapInvoice("Steve", 48, date + "St");
        verify(sap).send(steveInvoice);
    }
}
