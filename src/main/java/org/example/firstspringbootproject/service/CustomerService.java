package org.example.firstspringbootproject.service;

import org.example.firstspringbootproject.entities.Branch;
import org.example.firstspringbootproject.entities.Customer;
import org.example.firstspringbootproject.repository.BranchRepository;
import org.example.firstspringbootproject.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BranchRepository branchRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            BranchRepository branchRepository
    ) {
        this.customerRepository = customerRepository;
        this.branchRepository = branchRepository;
    }

    public Customer createCustomer(Customer customer, Long branchId) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException(
                    "A customer with this email already exists"
            );
        }

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Branch not found with ID: " + branchId
                        )
                );

        customer.setBranch(branch);

        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with ID: " + customerId
                        )
                );
    }

    @Transactional(readOnly = true)
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with email: " + email
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomersByBranch(Long branchId) {
        if (!branchRepository.existsById(branchId)) {
            throw new RuntimeException(
                    "Branch not found with ID: " + branchId
            );
        }

        return customerRepository.findByBranchBranchId(branchId);
    }

    public Customer updateCustomer(
            Long customerId,
            Customer newCustomerData
    ) {
        Customer existingCustomer = getCustomerById(customerId);

        if (!existingCustomer.getEmail().equals(newCustomerData.getEmail())
                && customerRepository.existsByEmail(
                newCustomerData.getEmail()
        )) {
            throw new RuntimeException(
                    "A customer with this email already exists"
            );
        }

        existingCustomer.setFirstName(newCustomerData.getFirstName());
        existingCustomer.setLastName(newCustomerData.getLastName());
        existingCustomer.setEmail(newCustomerData.getEmail());
        existingCustomer.setPhone(newCustomerData.getPhone());
        existingCustomer.setDateOfBirth(
                newCustomerData.getDateOfBirth()
        );
        existingCustomer.setKycStatus(newCustomerData.getKycStatus());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);
        customerRepository.delete(customer);
    }
}