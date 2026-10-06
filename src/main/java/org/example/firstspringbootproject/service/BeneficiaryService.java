package org.example.firstspringbootproject.service;

import org.example.firstspringbootproject.entities.Beneficiary;
import org.example.firstspringbootproject.repository.BeneficiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    public Beneficiary getBeneficiaryById(Long id) {
        return beneficiaryRepository.findById(id).orElse(null);
    }

    public Beneficiary createBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    public Beneficiary updateBeneficiary(
            Long id,
            Beneficiary beneficiary) {

        beneficiary.setBeneficiaryId(id);
        return beneficiaryRepository.save(beneficiary);
    }

    public void deleteBeneficiary(Long id) {
        beneficiaryRepository.deleteById(id);
    }
}
