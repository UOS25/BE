package uos.uos25.employee.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.employee.dto.request.EmployeeCreateReqeustDTO;
import uos.uos25.employee.dto.request.EmployeeCreateResponseDTO;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Tag(name = "직원")
public class EmployeeController {

    private final EmployeeService employeeService;

    // 모든 직원 가져오기
    @GetMapping
    public ResponseEntity<List<EmployeeCreateReqeustDTO>> findAll() {
        List<EmployeeCreateReqeustDTO> employeeCreateReqeustDTOS = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeCreateReqeustDTOS);
    }

    // 직원 저장
    @PostMapping
    public ResponseEntity<EmployeeCreateResponseDTO> join(@Valid @RequestBody EmployeeCreateReqeustDTO employeeCreateReqeustDTO) {
        Employee employee = employeeService.saveEmployee(employeeCreateReqeustDTO);

        return ResponseEntity.ok(EmployeeCreateResponseDTO.fromEntity(employee));
    }

    // 직원 수정
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable Long employeeId, @Valid @RequestBody EmployeeCreateReqeustDTO employeeCreateReqeustDTO) {
        employeeService.updateEmployee(employeeId, employeeCreateReqeustDTO);
        String msg = "수정되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 직원 퇴사
    @PutMapping("/retirement/{employeeId}")
    public ResponseEntity<?> retirementEmployee(@PathVariable Long employeeId) {
        employeeService.retirementEmployee(employeeId);
        String msg = "퇴사처리가 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 직원 기록 말소
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        String msg = "삭제되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
