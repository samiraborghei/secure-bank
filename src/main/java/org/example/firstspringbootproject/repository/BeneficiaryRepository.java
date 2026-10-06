package org.example.firstspringbootproject.repository;

import org.example.firstspringbootproject.entities.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository
        extends JpaRepository<Beneficiary, Long> {
}
