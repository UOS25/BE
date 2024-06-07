package uos.uos25.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uos.uos25.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByShopShopId(Long shopId);

    List<Employee> findAllByEmployeeName(String employeeName);
}
