package org.example.firstspringbootproject.repository;

import org.example.firstspringbootproject.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
