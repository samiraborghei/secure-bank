package org.example.firstspringbootproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.firstspringbootproject.entities.Account;
import org.example.firstspringbootproject.service.WithdrawService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/withdrawals")
@RequiredArgsConstructor
public class WithdrawController {

    private final WithdrawService withdrawService;

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> withdraw(
            @PathVariable Long accountId,
            @RequestParam BigDecimal amount
    ) {
        Account account = withdrawService.withdraw(accountId, amount);

        return ResponseEntity.ok(account);
    }
}