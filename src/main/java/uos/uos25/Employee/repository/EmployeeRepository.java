package uos.uos25.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uos.uos25.Employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
