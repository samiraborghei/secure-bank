package org.example.firstspringbootproject.repository;

import org.example.firstspringbootproject.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomer_CustomerId(Long customerId);

    @Query("SELECT a FROM Account a WHERE a.customer IN "
            + "(SELECT u.customer FROM UserAccount u WHERE u.username = :username)")
    Optional<Account> findByUsername(@Param("username") String username);
    Optional<Account> findByCustomerCustomerId(Long customerId);
}
