package org.example.firstspringbootproject.repository;

import org.example.firstspringbootproject.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
