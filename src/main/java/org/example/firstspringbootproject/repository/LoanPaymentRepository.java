package org.example.firstspringbootproject.repository;

import org.example.firstspringbootproject.entities.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanPaymentRepository
        extends JpaRepository<LoanPayment, Long> {
}
