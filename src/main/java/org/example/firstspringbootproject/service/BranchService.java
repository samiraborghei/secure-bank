package org.example.firstspringbootproject.service;

import org.example.firstspringbootproject.entities.Branch;
import org.example.firstspringbootproject.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Branch not found with ID: " + branchId
                        )
                );
    }

    public Branch updateBranch(Long branchId, Branch newBranchData) {
        Branch existingBranch = getBranchById(branchId);

        existingBranch.setBranchName(newBranchData.getBranchName());
        existingBranch.setIfscCode(newBranchData.getIfscCode());
        existingBranch.setCity(newBranchData.getCity());
        existingBranch.setAddress(newBranchData.getAddress());

        return branchRepository.save(existingBranch);
    }

    public void deleteBranch(Long branchId) {
        Branch existingBranch = getBranchById(branchId);
        branchRepository.delete(existingBranch);
    }
}
