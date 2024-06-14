package uos.uos25.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import uos.uos25.user.dto.request.EmployeeCreateReqeustDTO;
import uos.uos25.user.entity.Employee;
import uos.uos25.user.entity.PartTime;

@SpringBootTest
@Transactional
class EmployeeServiceTest {
    @Autowired EmployeeService employeeService;

    @Test
    void 직원_아이디로_조회() {
        Employee employee = employeeService.findById(1L);

        assertThat(employee.getEmployeeName()).isEqualTo("박직원");
    }

    @Test
    void 직원_생성() {
        EmployeeCreateReqeustDTO employeeCreateReqeustDTO =
                new EmployeeCreateReqeustDTO(
                        "유현승",
                        "점원",
                        "990222-1056789",
                        12000,
                        PartTime.DAY.name(),
                        "110-485-577342",
                        "신한은행",
                        1L);
        Employee savedEmployee = employeeService.saveEmployee(employeeCreateReqeustDTO);

        assertThat(savedEmployee.getEmployeeId()).isNotNull();
        assertThat(savedEmployee.getEmployeeName()).isEqualTo("유현승");
        assertThat(savedEmployee.getPosition()).isEqualTo("점원");
        assertThat(savedEmployee.getRegistrationNumber()).isEqualTo("990222-1056789");
        assertThat(savedEmployee.getPartTime()).isEqualTo(PartTime.DAY);
        assertThat(savedEmployee.getAccount()).isEqualTo("110-485-577342");
        assertThat(savedEmployee.getBank()).isEqualTo("신한은행");
        assertThat(savedEmployee.getShop()).isNotNull();
        assertThat(savedEmployee.getFiredDate()).isNull();
    }
}
