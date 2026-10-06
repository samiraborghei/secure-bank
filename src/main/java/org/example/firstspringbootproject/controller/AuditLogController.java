package org.example.firstspringbootproject.controller;

import org.example.firstspringbootproject.entities.AuditLog;
import org.example.firstspringbootproject.service.AuditLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return auditLogService.getAllAuditLogs();
    }

    @GetMapping("/{id}")
    public AuditLog getAuditLogById(@PathVariable Long id) {
        return auditLogService.getAuditLogById(id);
    }

    @PostMapping
    public AuditLog createAuditLog(@RequestBody AuditLog auditLog) {
        return auditLogService.createAuditLog(auditLog);
    }

    @PutMapping("/{id}")
    public AuditLog updateAuditLog(
            @PathVariable Long id,
            @RequestBody AuditLog auditLog) {

        return auditLogService.updateAuditLog(id, auditLog);
    }

    @DeleteMapping("/{id}")
    public void deleteAuditLog(@PathVariable Long id) {
        auditLogService.deleteAuditLog(id);
    }
}
