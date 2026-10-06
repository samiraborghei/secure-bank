package org.example.firstspringbootproject.controller;

import org.springframework.security.core.Authentication;
import org.example.firstspringbootproject.entities.Account;
import org.example.firstspringbootproject.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/{id}")
    public Account updateAccount(
            @PathVariable Long id,
            @RequestBody Account account) {

        return accountService.updateAccount(id, account);
    }
    @GetMapping("/my-account")
    public ResponseEntity<Account> getMyAccount(Authentication authentication) {

        System.out.println("MY-ACCOUNT CONTROLLER REACHED");
        System.out.println("Logged in username: " + authentication.getName());

        String username = authentication.getName();

        Account account = accountService.getAccountByUsername(username);

        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}
