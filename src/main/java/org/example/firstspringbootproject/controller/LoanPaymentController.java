package org.example.firstspringbootproject.controller;

import org.example.firstspringbootproject.entities.LoanPayment;
import org.example.firstspringbootproject.service.LoanPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan-payments")
public class LoanPaymentController {

    private final LoanPaymentService loanPaymentService;

    public LoanPaymentController(LoanPaymentService loanPaymentService) {
        this.loanPaymentService = loanPaymentService;
    }

    @GetMapping
    public List<LoanPayment> getAllLoanPayments() {
        return loanPaymentService.getAllLoanPayments();
    }

    @GetMapping("/{id}")
    public LoanPayment getLoanPaymentById(@PathVariable Long id) {
        return loanPaymentService.getLoanPaymentById(id);
    }

    @PostMapping
    public LoanPayment createLoanPayment(
            @RequestBody LoanPayment loanPayment) {

        return loanPaymentService.createLoanPayment(loanPayment);
    }

    @PutMapping("/{id}")
    public LoanPayment updateLoanPayment(
            @PathVariable Long id,
            @RequestBody LoanPayment loanPayment) {

        return loanPaymentService.updateLoanPayment(id, loanPayment);
    }

    @DeleteMapping("/{id}")
    public void deleteLoanPayment(@PathVariable Long id) {
        loanPaymentService.deleteLoanPayment(id);
    }
}
