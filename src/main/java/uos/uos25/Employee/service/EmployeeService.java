package uos.uos25.Employee.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.Employee.DTO.EmployeeDTO;
import uos.uos25.Employee.entity.Employee;
import uos.uos25.Employee.entity.PartTime;
import uos.uos25.Employee.repository.EmployeeRepository;
import uos.uos25.shop.repository.ShopRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ShopRepository shopRepository;


    @Transactional
    // 모든 직원 리스트 가져오기
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    // 직원 생성
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(makeNewEmployee(employeeDTO));
    }

    @Transactional
    // 직원 수정
    public void updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Employee findEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("다음의 Id를 가진 employee가 없습니다: " + employeeId));

        toEmployee(employeeDTO, findEmployee);
    }

    @Transactional
    // 직원 퇴사
    public void retirementEmployee(Long employeeId) {
        Employee findEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("다음의 Id를 가진 employee가 없습니다: " + employeeId));

        // NONE은 근무하는 시간이 지정되지 않았음을 의미합니다. 해고, 퇴사 등이 이에 해당됩니다.
        findEmployee.setPartTime(PartTime.NONE);
        // 월급 정산 로직 필요
    }

    @Transactional
    // 직원 삭제: 해당 직원의 기록을 말소합니다. 연관되어 있는 receipt 정보까지 모두 소멸됩니다.
    public void deleteEmployee(Long employeeId) {
        Employee findEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("다음의 Id를 가진 employee가 없습니다: " + employeeId));

        employeeRepository.delete(findEmployee);
    }

    // 리팩터링 메소드
    // 새로운 employee 객체를 만들어 반환합니다. saveEmployee()에 사용됩니다.
    private Employee makeNewEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        toEmployee(employeeDTO, employee);
        return employee;
    }

    // 기존에 존재하는 employee 객체를 수정합니다. updateEmployee()에 사용됩니다.
    private void toEmployee(EmployeeDTO employeeDTO, Employee employee) {
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setRegistrationNumber(employeeDTO.getRegistrationNumber());
        employee.setSalary(employeeDTO.getSalary());
        employee.setPartTime(PartTime.valueOf(employeeDTO.getPartTime()));
        employee.setAccount(employeeDTO.getAccount());

        employee.setShop(shopRepository.findByShopName(employeeDTO.getShopName()));
    }

}
