package com.ivrs.repositories;

import com.ivrs.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByCdaoNo(String cdaoNo);
}
