package org.example.firstspringbootproject.repository;

import org.example.firstspringbootproject.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {
}
