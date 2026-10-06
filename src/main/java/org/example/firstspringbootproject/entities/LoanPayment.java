package org.example.firstspringbootproject.entities;

import jakarta.persistence.*;
import org.example.firstspringbootproject.entities.enums.LoanPaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_payment")
public class LoanPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Column(precision = 18, scale = 2)
    private BigDecimal amount;

    private LocalDate dueDate;

    private LocalDate paidDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private LoanPaymentStatus status;

    // Getters and Setters

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public LoanPaymentStatus getStatus() {
        return status;
    }

    public void setStatus(LoanPaymentStatus status) {
        this.status = status;
    }
}
