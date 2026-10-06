package org.example.firstspringbootproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.firstspringbootproject.entities.Account;
import org.example.firstspringbootproject.service.DepositService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/deposits")
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> deposit(
            @PathVariable Long accountId,
            @RequestParam BigDecimal amount
    ) {
        Account account = depositService.deposit(accountId, amount);

        return ResponseEntity.ok(account);
    }
}