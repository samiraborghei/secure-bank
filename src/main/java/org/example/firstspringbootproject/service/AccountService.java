package org.example.firstspringbootproject.service;

import org.example.firstspringbootproject.entities.Account;
import org.example.firstspringbootproject.entities.Customer;
import org.example.firstspringbootproject.entities.UserAccount;
import org.example.firstspringbootproject.repository.AccountRepository;
import org.example.firstspringbootproject.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserAccountRepository userAccountRepository;

    public AccountService(AccountRepository accountRepository,
                          UserAccountRepository userAccountRepository) {
        this.accountRepository = accountRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account createAccount(Account account) {
        validateAccountNumber(account);
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account account) {
        validateAccountNumber(account);
        account.setAccountId(id);
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    private void validateAccountNumber(Account account) {
        if (account.getAccountNumber() == null
                || account.getAccountNumber().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "accountNumber is required"
            );
        }
    }

    public Account getAccountByUsername(String username) {

        System.out.println("Looking for username: " + username);

        UserAccount user = userAccountRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        System.out.println("User found: " + user.getUsername());

        Customer customer = user.getCustomer();

        if (customer == null) {
            System.out.println("CUSTOMER IS NULL!");

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer not found"
            );
        }

        System.out.println("Customer ID: " + customer.getCustomerId());

        Account account = accountRepository
                .findByCustomerCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account not found"
                ));

        System.out.println("ACCOUNT FOUND!");
        System.out.println("Account number: " + account.getAccountNumber());
        System.out.println("Balance: " + account.getBalance());

        return account;
    }
}
