package org.example.firstspringbootproject.controller;

import org.example.firstspringbootproject.entities.Customer;
import org.example.firstspringbootproject.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<Customer> createCustomer(
            @PathVariable Long branchId,
            @RequestBody Customer customer
    ) {
        Customer createdCustomer =
                customerService.createCustomer(customer, branchId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCustomer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(
                customerService.getAllCustomers()
        );
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long customerId
    ) {
        return ResponseEntity.ok(
                customerService.getCustomerById(customerId)
        );
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(
            @PathVariable String email
    ) {
        return ResponseEntity.ok(
                customerService.getCustomerByEmail(email)
        );
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<Customer>> getCustomersByBranch(
            @PathVariable Long branchId
    ) {
        return ResponseEntity.ok(
                customerService.getCustomersByBranch(branchId)
        );
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody Customer customer
    ) {
        return ResponseEntity.ok(
                customerService.updateCustomer(
                        customerId,
                        customer
                )
        );
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long customerId
    ) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}