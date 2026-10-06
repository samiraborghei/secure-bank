package org.example.firstspringbootproject.controller;

import org.example.firstspringbootproject.entities.UserAccount;
import org.example.firstspringbootproject.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user-accounts")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(
            UserAccountService userAccountService
    ) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<UserAccount> createUserAccount(
            @PathVariable Long customerId,
            @RequestBody UserAccount userAccount
    ) {
        UserAccount createdAccount =
                userAccountService.createUserAccount(
                        userAccount
                        //customerId
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAccount);
    }

    @PostMapping("/staff")
    public ResponseEntity<UserAccount> createStaffUser(
            @RequestBody UserAccount userAccount
    ) {
        UserAccount createdAccount;
        try {
            createdAccount =
                    userAccountService.createStaffUser(userAccount);
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(null);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAccount);
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUserAccounts() {
        return ResponseEntity.ok(
                userAccountService.getAllUserAccounts()
        );
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<UserAccount> getUserAccountById(
            @PathVariable Long accountId
    ) {
        return ResponseEntity.ok(
                userAccountService.getUserAccountById(accountId)
        );
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserAccount> getByUsername(
            @PathVariable String username
    ) {
        return ResponseEntity.ok(
                userAccountService.getUserAccountByUsername(username)
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<UserAccount> getByCustomer(
            @PathVariable Long customerId
    ) {
        return ResponseEntity.ok(
                userAccountService.getUserAccountByCustomer(customerId)
        );
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<UserAccount> updateUserAccount(
            @PathVariable Long accountId,
            @RequestBody UserAccount userAccount
    ) {
        return ResponseEntity.ok(
                userAccountService.updateUserAccount(
                        accountId,
                        userAccount
                )
        );
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteUserAccount(
            @PathVariable Long accountId
    ) {
        userAccountService.deleteUserAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}