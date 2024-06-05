package uos.uos25.employee.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.employee.dto.request.EmployeeCreateReqeustDTO;
import uos.uos25.employee.dto.request.EmployeeUpdateReqeustDTO;
import uos.uos25.employee.dto.response.EmployeeCreateResponseDTO;
import uos.uos25.employee.dto.response.EmployeeGetResponseDTO;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Tag(name = "직원")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeCreateResponseDTO> join(
            @Valid @RequestBody EmployeeCreateReqeustDTO employeeCreateReqeustDTO) {
        Employee employee = employeeService.saveEmployee(employeeCreateReqeustDTO);

        return ResponseEntity.ok(EmployeeCreateResponseDTO.fromEntity(employee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeGetResponseDTO>> findAll() {
        List<Employee> employees = employeeService.findAllEmployees();
        List<EmployeeGetResponseDTO> employeeGetResponseDTOS =
                employees.stream()
                        .map(employee -> EmployeeGetResponseDTO.fromEntity(employee))
                        .toList();

        return ResponseEntity.ok(employeeGetResponseDTOS);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeGetResponseDTO> findById(@PathVariable Long employeeId) {
        Employee employee = employeeService.findById(employeeId);

        return ResponseEntity.ok(EmployeeGetResponseDTO.fromEntity(employee));
    }

    @PatchMapping
    public ResponseEntity<Void> updateEmployee(
            @Valid @RequestBody EmployeeUpdateReqeustDTO employeeUpdateReqeustDTO) {
        employeeService.updateEmployee(employeeUpdateReqeustDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "직원 퇴사")
    @PatchMapping("/{employeeId}")
    public ResponseEntity<Void> retirementEmployee(@PathVariable Long employeeId) {
        employeeService.retirementEmployee(employeeId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "직원 말소")
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
