package uos.uos25.Employee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uos.uos25.Employee.DTO.EmployeeDTO;
import uos.uos25.Employee.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeService employeeService;

    // 직원 저장
    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody EmployeeDTO employeeDTO) {

        employeeService.saveEmployee(employeeDTO);
        String msg = "저장되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    // 빈 칸이 있는지 체킹. 해당 과정은 직원 수정, 직원 퇴사에 동일하게 적용됩니다.
    // Enum에 대한 예외처리를 하지 않았습니다. 프론트단에서 지정된 Enum 값만 선택할 수 있게 인터페이스를 짜주시면 감사하겠습니다.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleValidationException() {
//        String msg = "모든 정보를 입력했는지 다시 확인해주세요.";
//        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
//    }

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
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeDTO employeeDTO) {
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
