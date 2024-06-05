package uos.uos25.employee.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.employee.DTO.EmployeeDTO;
import uos.uos25.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Tag(name = "직원")
public class EmployeeController {

    private final EmployeeService employeeService;

    // 모든 직원 가져오기
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDTOS);
    }
    // 직원 저장
    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody EmployeeDTO employeeDTO) {

        employeeService.saveEmployee(employeeDTO);
        String msg = "저장되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    // 직원 수정
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(employeeId, employeeDTO);
        String msg = "수정되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 직원 퇴사
    @PutMapping("/retirement/{employeeId}")
    public ResponseEntity<?> retirementEmployee(
            @PathVariable Long employeeId) {
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
