package org.example.firstspringbootproject.controller;

import org.example.firstspringbootproject.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> transferMoney(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam BigDecimal amount
    ) {

        transferService.transferMoney(
                fromAccountId,
                toAccountId,
                amount
        );

        return ResponseEntity.ok("Transfer successful");
    }
}