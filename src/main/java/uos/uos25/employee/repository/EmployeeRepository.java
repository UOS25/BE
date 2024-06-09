package uos.uos25.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uos.uos25.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByShopShopId(Long shopId);

    List<Employee> findAllByEmployeeName(String employeeName);

    List<Employee> findByShopShopIdAndEmployeeNameContaining(Long shopId, String employeeName);

    List<Employee> findAllByPosition(String position);

    @Query("SELECT e FROM Employee e where e.position != '본사'")
    List<Employee> findAllNotHeadquarter();
}
