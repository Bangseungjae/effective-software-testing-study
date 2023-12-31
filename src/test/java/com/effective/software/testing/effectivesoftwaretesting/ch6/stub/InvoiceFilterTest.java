package com.effective.software.testing.effectivesoftwaretesting.ch6.stub;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InvoiceFilterTest {


    /**
     * 의존성 스텁화
     */
    @Test
    void filterInvoices() {
        IssuedInvoices issuedInvoices = mock(IssuedInvoices.class);

        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice steve = new Invoice("Steve", 99);
        Invoice frank = new Invoice("Frank", 100);
        List<Invoice> listOfInvoices = Arrays.asList(mauricio, steve, frank);

        when(issuedInvoices.all()).thenReturn(listOfInvoices);

        InvoiceFilter filter = new InvoiceFilter(issuedInvoices);

        assertThat(filter.lowValueInvoices())
                .containsExactlyInAnyOrder(mauricio, steve);
    }

}
