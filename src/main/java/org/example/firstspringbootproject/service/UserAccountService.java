package org.example.firstspringbootproject.service;

import lombok.RequiredArgsConstructor;
import org.example.firstspringbootproject.entities.Customer;
import org.example.firstspringbootproject.entities.UserAccount;
import org.example.firstspringbootproject.repository.CustomerRepository;
import org.example.firstspringbootproject.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    public UserAccount createUser(UserAccount user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userAccountRepository.save(user);
    }
    public UserAccount createStaffUser(UserAccount userAccount) {

        String username = userAccount.getUsername();
        boolean userExists = userAccountRepository.existsByUsername(username);

        if (userExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This username is already taken"
            );
        }

        userAccount.setPasswordHash(
                passwordEncoder.encode(userAccount.getPasswordHash())
        );

        return userAccountRepository.save(userAccount);
    }

    public UserAccount createUserAccount(
            UserAccount userAccount
//            Long customerId
    ) {
//        if (userAccountRepository.existsByUsername(
//                userAccount.getUsername()
//        )) {
//            throw new ResponseStatusException(
//                    HttpStatus.CONFLICT,
//                    "This username is already taken"
//            );
//        }

//        if (userAccountRepository
//                .findByCustomerCustomerId(customerId)
//                .isPresent()) {
//            throw new RuntimeException(
//                    "This customer already has a user account"
//            );
//        }

//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() ->
//                        new RuntimeException(
//                                "Customer not found with ID: " + customerId
//                        )
//                );
//
//        userAccount.setCustomer(customer);
        userAccount.setPasswordHash(
                passwordEncoder.encode(userAccount.getPasswordHash())
        );

        return userAccountRepository.save(userAccount);
    }

    @Transactional(readOnly = true)
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserAccount getUserAccountById(Long accountId) {
        return userAccountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User account not found with ID: " + accountId
                        )
                );
    }

    @Transactional(readOnly = true)
    public UserAccount getUserAccountByUsername(String username) {
        return userAccountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User account not found: " + username
                        )
                );
    }

    @Transactional(readOnly = true)
    public UserAccount getUserAccountByCustomer(Long customerId) {
        return userAccountRepository
                .findByCustomerCustomerId(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No account found for customer ID: "
                                        + customerId
                        )
                );
    }

    public UserAccount updateUserAccount(
            Long accountId,
            UserAccount newAccountData
    ) {
        UserAccount existingAccount =
                getUserAccountById(accountId);

        String newUsername = newAccountData.getUsername();

        if (!existingAccount.getUsername().equals(newUsername)
                && userAccountRepository.existsByUsername(newUsername)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This username is already taken"
            );
        }

        existingAccount.setUsername(newUsername);
        existingAccount.setPasswordHash(newAccountData.getPasswordHash());

        // Add other fields from your UserAccount entity here:
        // existingAccount.setStatus(newAccountData.getStatus());

        return userAccountRepository.save(existingAccount);
    }

    public void deleteUserAccount(Long accountId) {
        UserAccount account = getUserAccountById(accountId);
        userAccountRepository.delete(account);
    }
}
