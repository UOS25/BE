package uos.uos25.employee.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.employee.dto.request.EmployeeCreateReqeustDTO;
import uos.uos25.employee.dto.request.EmployeeUpdateReqeustDTO;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.entity.PartTime;
import uos.uos25.employee.exception.EmployeeNotFound;
import uos.uos25.employee.repository.EmployeeRepository;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ShopService shopService;

    @Transactional
    // 모든 직원 리스트를 가져온 후, DTO 리스트로 반환합니다.
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFound());
    }

    @Transactional
    public Employee saveEmployee(EmployeeCreateReqeustDTO employeeCreateReqeustDTO) {
        Employee employee = convertToEntity(employeeCreateReqeustDTO);

        return employeeRepository.save(employee);
    }

    @Transactional
    // 직원 수정
    public void updateEmployee(EmployeeUpdateReqeustDTO employeeUpdateReqeustDTO) {
        Shop shop = shopService.findShopById(employeeUpdateReqeustDTO.getShopId());
        Employee findEmployee =
                employeeRepository
                        .findById(employeeUpdateReqeustDTO.getEmployeeId())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "다음의 Id를 가진 employee가 없습니다: "
                                                        + employeeUpdateReqeustDTO
                                                                .getEmployeeId()));

        findEmployee.update(
                employeeUpdateReqeustDTO.getEmployeeName(),
                employeeUpdateReqeustDTO.getPosition(),
                employeeUpdateReqeustDTO.getRegistrationNumber(),
                employeeUpdateReqeustDTO.getSalary(),
                employeeUpdateReqeustDTO.getPartTime(),
                employeeUpdateReqeustDTO.getAccount(),
                shop);
    }

    @Transactional
    // 직원 퇴사
    public void retirementEmployee(Long employeeId) {
        Employee findEmployee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "다음의 Id를 가진 employee가 없습니다: " + employeeId));

        // NONE은 근무하는 시간이 지정되지 않았음을 의미합니다. 해고, 퇴사 등이 이에 해당됩니다.
        findEmployee.setPartTime(PartTime.NONE);
        // 직원이 퇴사한 시간을 현재 시간으로 설정합니다.
        LocalDateTime now = LocalDateTime.now().withNano(0);
        findEmployee.setFiredDate(now);
        // 매월 1일을 기준으로 지금까지 일했던 날짜와 시간을 계산합니다.
    }

    @Transactional
    // 직원 삭제: 해당 직원의 기록을 말소합니다. 연관되어 있는 receipt 정보까지 모두 소멸됩니다.
    public void deleteEmployee(Long employeeId) {
        Employee findEmployee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "다음의 Id를 가진 employee가 없습니다: " + employeeId));

        employeeRepository.delete(findEmployee);
    }

    private Employee convertToEntity(EmployeeCreateReqeustDTO employeeCreateReqeustDTO) {
        Shop shop = shopService.findShopById(employeeCreateReqeustDTO.getShopId());

        return Employee.builder()
                .employeeName(employeeCreateReqeustDTO.getEmployeeName())
                .position(employeeCreateReqeustDTO.getPosition())
                .registrationNumber(employeeCreateReqeustDTO.getRegistrationNumber())
                .salary(employeeCreateReqeustDTO.getSalary())
                .partTime(PartTime.valueOf(employeeCreateReqeustDTO.getPartTime()))
                .account(employeeCreateReqeustDTO.getAccount())
                .employmentDate(LocalDateTime.now())
                .shop(shop)
                .build();
    }
}
