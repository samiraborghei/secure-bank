package org.example.firstspringbootproject.service;

import jakarta.transaction.Transactional;
import org.example.firstspringbootproject.entities.Account;
import org.example.firstspringbootproject.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(
            Long fromAccountId,
            Long toAccountId,
            BigDecimal amount
    ) {

        if (fromAccountId.equals(toAccountId)) {
            throw new RuntimeException(
                    "Sender and receiver accounts cannot be the same"
            );
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "Transfer amount must be greater than zero"
            );
        }

        Account fromAccount = accountRepository
                .findById(fromAccountId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sender account not found"
                        )
                );

        Account toAccount = accountRepository
                .findById(toAccountId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Receiver account not found"
                        )
                );

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException(
                    "Insufficient funds"
            );
        }

        fromAccount.setBalance(
                fromAccount.getBalance().subtract(amount)
        );

        toAccount.setBalance(
                toAccount.getBalance().add(amount)
        );

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}