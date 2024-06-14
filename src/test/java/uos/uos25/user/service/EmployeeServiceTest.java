package uos.uos25.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import uos.uos25.user.dto.request.EmployeeCreateReqeustDTO;
import uos.uos25.user.dto.request.EmployeeUpdateReqeustDTO;
import uos.uos25.user.entity.Employee;
import uos.uos25.user.entity.PartTime;

@SpringBootTest
@Transactional
class EmployeeServiceTest {
    @Autowired EmployeeService employeeService;

    @Test
    void 직원_아이디로_조회() {
        // given, when
        Employee employee = employeeService.findById(1L);

        // then
        assertThat(employee.getEmployeeName()).isEqualTo("박직원");
    }

    @Test
    void 직원_지점_아이디로_조회() {
        // given, when
        List<Employee> employees = employeeService.findEmployeeByShopId(1L);

        // then
        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    void 직원_생성() {
        // given
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

        // when
        Employee savedEmployee = employeeService.saveEmployee(employeeCreateReqeustDTO);

        // then
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

    @Test
    void 직원_수정() {
        // given
        EmployeeUpdateReqeustDTO employeeUpdateReqeustDTO =
                EmployeeUpdateReqeustDTO.builder()
                        .employeeId(1L)
                        .employeeName("수정된이름")
                        .shopId(1L)
                        .build();

        // when
        Employee updatedEmployee = employeeService.updateEmployee(employeeUpdateReqeustDTO);

        // then
        assertThat(updatedEmployee.getEmployeeName()).isEqualTo("수정된이름");
    }

    @Test
    void 직원_수정_Id_없을_시_예외() {
        // given
        EmployeeUpdateReqeustDTO employeeUpdateReqeustDTO =
                EmployeeUpdateReqeustDTO.builder()
                        .employeeId(3L)
                        .employeeName("수정된이름")
                        .shopId(1L)
                        .build();

        // when, then
        assertThatThrownBy(() -> employeeService.updateEmployee(employeeUpdateReqeustDTO))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 직원_퇴사() {
        // given
        Long employeeId = 1L;

        // when
        Employee retiredEmployee = employeeService.retirementEmployee(employeeId);

        // then
        assertThat(retiredEmployee.getFiredDate()).isNotNull();
    }
}
