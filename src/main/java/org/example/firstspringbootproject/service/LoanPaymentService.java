package org.example.firstspringbootproject.service;

import org.example.firstspringbootproject.entities.LoanPayment;
import org.example.firstspringbootproject.repository.LoanPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanPaymentService {

    private final LoanPaymentRepository loanPaymentRepository;

    public LoanPaymentService(LoanPaymentRepository loanPaymentRepository) {
        this.loanPaymentRepository = loanPaymentRepository;
    }

    public List<LoanPayment> getAllLoanPayments() {
        return loanPaymentRepository.findAll();
    }

    public LoanPayment getLoanPaymentById(Long id) {
        return loanPaymentRepository.findById(id).orElse(null);
    }

    public LoanPayment createLoanPayment(LoanPayment loanPayment) {
        return loanPaymentRepository.save(loanPayment);
    }

    public LoanPayment updateLoanPayment(
            Long id,
            LoanPayment loanPayment) {

        loanPayment.setPaymentId(id);
        return loanPaymentRepository.save(loanPayment);
    }

    public void deleteLoanPayment(Long id) {
        loanPaymentRepository.deleteById(id);
    }
}
