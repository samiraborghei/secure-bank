package org.example.firstspringbootproject.service;

import org.example.firstspringbootproject.entities.AuditLog;
import org.example.firstspringbootproject.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    public AuditLog getAuditLogById(Long id) {
        return auditLogRepository.findById(id).orElse(null);
    }

    public AuditLog createAuditLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    public AuditLog updateAuditLog(Long id, AuditLog auditLog) {
        auditLog.setLogId(id);
        return auditLogRepository.save(auditLog);
    }

    public void deleteAuditLog(Long id) {
        auditLogRepository.deleteById(id);
    }
}
