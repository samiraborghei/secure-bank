package org.example.firstspringbootproject.service;

import lombok.RequiredArgsConstructor;
import org.example.firstspringbootproject.entities.Account;
import org.example.firstspringbootproject.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WithdrawService {

    private final AccountRepository accountRepository;

    @Transactional
    public Account withdraw(Long accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found with ID: " + accountId
                        )
                );

        // Amount must be greater than 0
        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Withdrawal amount must be greater than 0"
            );
        }

        // Make sure there is enough money
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException(
                    "Insufficient funds"
            );
        }

        // Subtract money
        BigDecimal newBalance =
                account.getBalance().subtract(amount);

        account.setBalance(newBalance);

        return accountRepository.save(account);
    }
}