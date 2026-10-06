package org.example.firstspringbootproject.service;

import org.example.firstspringbootproject.entities.Account;
import org.example.firstspringbootproject.entities.Card;
import org.example.firstspringbootproject.repository.AccountRepository;
import org.example.firstspringbootproject.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public CardService(
            CardRepository cardRepository,
            AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public Card updateCard(Long id, Card card) {
        card.setCardId(id);
        return cardRepository.save(card);
    }
    public Card createCard(Card card) {

        Long accountId = card.getAccount().getAccountId();

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        card.setAccount(account);

        return cardRepository.save(card);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
