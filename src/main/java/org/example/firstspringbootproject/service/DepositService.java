package org.example.firstspringbootproject.service;

import lombok.RequiredArgsConstructor;
import org.example.firstspringbootproject.entities.Account;
import org.example.firstspringbootproject.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DepositService {

    private final AccountRepository accountRepository;

    @Transactional
    public Account deposit(Long accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found with ID: " + accountId
                        )
                );

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "Deposit amount must be greater than 0"
            );
        }

        BigDecimal newBalance =
                account.getBalance().add(amount);

        account.setBalance(newBalance);

        return accountRepository.save(account);
    }
}
