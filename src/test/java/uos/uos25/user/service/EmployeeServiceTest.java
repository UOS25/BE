package uos.uos25.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import uos.uos25.shop.repository.ShopRepository;
import uos.uos25.user.entity.Employee;
import uos.uos25.user.repository.EmployeeRepository;

@SpringBootTest
@Transactional
class EmployeeServiceTest {
    @Autowired EmployeeService employeeService;

    @Test
    void 직원_아이디로_조회() {
        Employee employee = employeeService.findById(1L);

        assertThat(employee.getEmployeeName()).isEqualTo("박직원");
    }

}
