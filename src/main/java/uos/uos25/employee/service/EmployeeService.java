package uos.uos25.employee.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.employee.dto.request.EmployeeCreateReqeustDTO;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.entity.PartTime;
import uos.uos25.employee.exception.EmployeeNotFound;
import uos.uos25.employee.repository.EmployeeRepository;
import uos.uos25.shop.repository.ShopRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ShopRepository shopRepository;

    @Transactional
    // 모든 직원 리스트를 가져온 후, DTO 리스트로 반환합니다.
    public List<EmployeeCreateReqeustDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFound());
    }

    @Transactional
    // 직원 생성
    public Employee saveEmployee(EmployeeCreateReqeustDTO employeeCreateReqeustDTO) {
        return employeeRepository.save(makeNewEmployee(employeeCreateReqeustDTO));
    }

    @Transactional
    // 직원 수정
    public void updateEmployee(Long employeeId, EmployeeCreateReqeustDTO employeeCreateReqeustDTO) {
        Employee findEmployee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "다음의 Id를 가진 employee가 없습니다: " + employeeId));

        convertToEmployee(employeeCreateReqeustDTO, findEmployee);
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

    // 리팩터링 메소드
    // 새로운 employee 객체를 만들어 반환합니다. saveEmployee()에 사용됩니다.
    private Employee makeNewEmployee(EmployeeCreateReqeustDTO employeeCreateReqeustDTO) {
        Employee employee = new Employee();
        // DTO에서 Employee로 변환합니다.
        convertToEmployee(employeeCreateReqeustDTO, employee);
        // 현재 시간을 세팅합니다.
        employee.setEmploymentDate(LocalDateTime.now().withNano(0));
        return employee;
    }

    // EmployeeDTO를 Employee로 변환합니다. updateEmployee()에 사용됩니다.
    private void convertToEmployee(EmployeeCreateReqeustDTO employeeCreateReqeustDTO, Employee employee) {
        employee.setEmployeeName(employeeCreateReqeustDTO.getEmployeeName());
        employee.setPosition(employeeCreateReqeustDTO.getPosition());
        employee.setRegistrationNumber(employeeCreateReqeustDTO.getRegistrationNumber());
        employee.setSalary(employeeCreateReqeustDTO.getSalary());
        employee.setPartTime(PartTime.valueOf(employeeCreateReqeustDTO.getPartTime()));
        employee.setAccount(employeeCreateReqeustDTO.getAccount());
        employee.setShop(shopRepository.findByShopName(employeeCreateReqeustDTO.getShopName()));
    }

    // Employee를 DTO로 변환합니다.
    private EmployeeCreateReqeustDTO convertToDTO(Employee employee) {
        EmployeeCreateReqeustDTO employeeCreateReqeustDTO = new EmployeeCreateReqeustDTO();
        employeeCreateReqeustDTO.setEmployeeName(employee.getEmployeeName());
        employeeCreateReqeustDTO.setPosition(employee.getPosition());
        employeeCreateReqeustDTO.setRegistrationNumber(employee.getRegistrationNumber());
        employeeCreateReqeustDTO.setSalary(employee.getSalary());
        employeeCreateReqeustDTO.setPartTime(employee.getPartTime().toString());
        employeeCreateReqeustDTO.setAccount(employee.getAccount());
        employeeCreateReqeustDTO.setShopName(employee.getShop().getShopName());
        return employeeCreateReqeustDTO;
    }
}
