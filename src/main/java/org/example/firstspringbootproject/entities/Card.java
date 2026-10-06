package org.example.firstspringbootproject.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

import org.example.firstspringbootproject.entities.enums.CardType;
import org.example.firstspringbootproject.entities.enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @Column(unique = true, length = 19)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CardType cardType;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CardStatus status;


    // Getters and Setters

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }
}
