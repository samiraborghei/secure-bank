package org.example.firstspringbootproject.repository;

import org.example.firstspringbootproject.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {
}
