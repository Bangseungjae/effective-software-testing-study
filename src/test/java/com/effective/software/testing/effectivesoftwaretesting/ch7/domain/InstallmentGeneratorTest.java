package com.effective.software.testing.effectivesoftwaretesting.ch7.domain;

import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.InstallmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InstallmentGeneratorTest {

    @Mock
    private InstallmentRepository repository;

    @Test
    void checkInstallments() {
        InstallmentGenerator generator = new InstallmentGenerator(repository);

        ShoppingCart cart = new ShoppingCart(100.0);
        generator.generateInstallments(cart, 10);

        ArgumentCaptor<Installment> captor = ArgumentCaptor.forClass(Installment.class);

        verify(repository, times(10)).persist(captor.capture());
        List<Installment> allInstallments = captor.getAllValues();

        assertThat(allInstallments)
                .hasSize(10)
                .allMatch(i -> i.value() == 10);

        for (int month = 1; month <= 10; month++) {
            final LocalDate dueDate = LocalDate.now().plusMonths(month);
            assertThat(allInstallments)
                    .anyMatch(i -> i.date().equals(dueDate));
        }
    }

    @Test
    void checkInstallments2() {
        InstallmentGenerator generator = new InstallmentGenerator(repository);

        ShoppingCart cart = new ShoppingCart(100.0);
        List<Installment> allInstallments = generator.generateInstallments2(cart, 10);

        assertThat(allInstallments)
                .hasSize(10)
                .allMatch(i -> i.value() == 10);

        for (int month = 1; month <= 10; month++) {
            final LocalDate dueDate = LocalDate.now().plusMonths(month);
            assertThat(allInstallments)
                    .anyMatch(i -> i.date().equals(dueDate));
        }
    }
}
