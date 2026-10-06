package org.example.firstspringbootproject.controller;

import org.example.firstspringbootproject.entities.Branch;
import org.example.firstspringbootproject.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<Branch> createBranch(
            @RequestBody Branch branch
    ) {
        Branch createdBranch = branchService.createBranch(branch);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdBranch);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<Branch> getBranchById(
            @PathVariable Long branchId
    ) {
        return ResponseEntity.ok(
                branchService.getBranchById(branchId)
        );
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<Branch> updateBranch(
            @PathVariable Long branchId,
            @RequestBody Branch branch
    ) {
        return ResponseEntity.ok(
                branchService.updateBranch(branchId, branch)
        );
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<Void> deleteBranch(
            @PathVariable Long branchId
    ) {
        branchService.deleteBranch(branchId);
        return ResponseEntity.noContent().build();
    }
}