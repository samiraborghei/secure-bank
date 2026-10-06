package org.example.firstspringbootproject.service;

import org.example.firstspringbootproject.entities.Loan;
import org.example.firstspringbootproject.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, Loan loan) {
        loan.setLoanId(id);
        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}
