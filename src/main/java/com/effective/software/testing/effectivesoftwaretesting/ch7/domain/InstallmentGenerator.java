package com.effective.software.testing.effectivesoftwaretesting.ch7.domain;

import com.effective.software.testing.effectivesoftwaretesting.ch7.ports.InstallmentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstallmentGenerator {

    private InstallmentRepository repository;

    public InstallmentGenerator(InstallmentRepository repository) {
        this.repository = repository;
    }

    public void generateInstallments(ShoppingCart cart, int numberOfInstallments) {
        LocalDate nextInstallmentDueDate = LocalDate.now();

        double amountPerInstallment = cart.getValue() / numberOfInstallments;

        for (int i = 1; i <= numberOfInstallments; i++) {
            nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1);

            Installment newInstallment = new Installment(nextInstallmentDueDate, amountPerInstallment);
            repository.persist(newInstallment);
        }
    }

    public List<Installment> generateInstallments2(ShoppingCart cart, int numberOfInstallments) {
        List<Installment> generatedInstallments = new ArrayList<>();

        LocalDate nextInstallmentDueDate = LocalDate.now();

        double amountPerInstallment = cart.getValue() / numberOfInstallments;

        for (int i = 1; i <= numberOfInstallments; i++) {
            nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1);

            Installment newInstallment = new Installment(nextInstallmentDueDate, amountPerInstallment);
            repository.persist(newInstallment);

            generatedInstallments.add(newInstallment);
        }

        return generatedInstallments;
    }
}
