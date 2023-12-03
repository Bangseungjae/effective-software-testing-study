package com.effective.software.testing.effectivesoftwaretesting.ch7.ports;

import com.effective.software.testing.effectivesoftwaretesting.ch7.domain.Installment;

public interface InstallmentRepository {

    void persist(Installment installment);
}
