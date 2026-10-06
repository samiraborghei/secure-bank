package org.example.firstspringbootproject.controller;

import org.example.firstspringbootproject.entities.Beneficiary;
import org.example.firstspringbootproject.service.BeneficiaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @GetMapping
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryService.getAllBeneficiaries();
    }

    @GetMapping("/{id}")
    public Beneficiary getBeneficiaryById(@PathVariable Long id) {
        return beneficiaryService.getBeneficiaryById(id);
    }

    @PostMapping
    public Beneficiary createBeneficiary(
            @RequestBody Beneficiary beneficiary) {

        return beneficiaryService.createBeneficiary(beneficiary);
    }

    @PutMapping("/{id}")
    public Beneficiary updateBeneficiary(
            @PathVariable Long id,
            @RequestBody Beneficiary beneficiary) {

        return beneficiaryService.updateBeneficiary(id, beneficiary);
    }

    @DeleteMapping("/{id}")
    public void deleteBeneficiary(@PathVariable Long id) {
        beneficiaryService.deleteBeneficiary(id);
    }
}
