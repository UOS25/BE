package uos.uos25.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uos.uos25.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
