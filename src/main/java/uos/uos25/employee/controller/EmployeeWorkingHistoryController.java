package uos.uos25.employee.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.employee.dto.EmployeeWorkingHistoryDTO;
import uos.uos25.employee.dto.response.EmployeeWorkingHistoryGetResponseDTO;
import uos.uos25.employee.entity.EmployeeWorkingHistory;
import uos.uos25.employee.repository.EmployeeRepository;
import uos.uos25.employee.service.EmployeeWorkingHistoryService;

@RestController
@RequestMapping("/employee/work")
@RequiredArgsConstructor
@Tag(name = "직원기록")
public class EmployeeWorkingHistoryController {

    private final EmployeeWorkingHistoryService employeeWorkingHistoryService;
    private final EmployeeRepository employeeRepository;

    // 직원의 출퇴근 목록 불러오기
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<EmployeeWorkingHistoryDTO>> findAllHistories(
            @PathVariable long employeeId) {
        List<EmployeeWorkingHistoryDTO> allEmployeeWorkingHistories =
                employeeWorkingHistoryService.findAllEmployeeWorkingHistories(employeeId);
        return ResponseEntity.ok(allEmployeeWorkingHistories);
    }

    // 직원의 출근 처리
    @GetMapping("/{employeeId}/start")
    public ResponseEntity<?> startWorking(@PathVariable Long employeeId) {
        // service에서 startWorking을 호출합니다.
        employeeWorkingHistoryService.startWorking(employeeId);

        // employeeId로 출근한 employee의 이름을 가져옵니다.
        // employeeRepository.findById의 리턴 값이 Optional이지만, 일단 예외처리는 하지 않았습니다.
        // 해당 getMapping까지 접근하기 위해서는 올바른 employeeId가 필요하기 때문입니다.
        // (url을 직접 수정하지 않는 이상 예외가 발생하기 어렵다고 생각했습니다. 필요하다면 처리하겠습니다.)
        String employeeName = employeeRepository.findById(employeeId).get().getEmployeeName();
        String msg = employeeName + "님, 반갑습니다! 출근 처리가 완료되었습니다.";

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 직원의 퇴근 처리
    @PutMapping("/{employeeId}/end")
    public ResponseEntity<?> endWorking(@PathVariable Long employeeId) {

        // service에서 endWorking을 호출합니다.
        employeeWorkingHistoryService.endWorking(employeeId);

        String employeeName = employeeRepository.findById(employeeId).get().getEmployeeName();
        String msg = employeeName + "님, 수고하셨습니다! 퇴근 처리가 완료되었습니다.";

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 임의로 직원의 출퇴근 기록을 추가
    @PostMapping("/addHistory")
    public ResponseEntity<?> addWorkingHistory(
            @Valid @RequestBody EmployeeWorkingHistoryDTO employeeWorkingHistoryDTO) {

        // service에서 addWorkingHistory를 호출합니다.
        employeeWorkingHistoryService.addWorkingHistory(employeeWorkingHistoryDTO);

        String msg = "출퇴근 기록이 추가되었습니다.";

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 직원의 출퇴근 기록을 삭제
    @DeleteMapping("/delete/{employeeWorkingHistoryId}")
    public ResponseEntity<?> deleteWorkingHistory(@PathVariable Long employeeWorkingHistoryId) {
        employeeWorkingHistoryService.deleteEmployeeWorkingHistory(employeeWorkingHistoryId);
        String msg = "삭제되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<EmployeeWorkingHistoryGetResponseDTO>> getByShopId(
            @PathVariable Long shopId) {
        List<EmployeeWorkingHistory> employeeWorkingHistories =
                employeeWorkingHistoryService.findByShopId(shopId);
        List<EmployeeWorkingHistoryGetResponseDTO> employeeWorkingHistoryGetResponseDTOS =
                employeeWorkingHistories.stream()
                        .map(
                                employeeWorkingHistory ->
                                        EmployeeWorkingHistoryGetResponseDTO.fromEntity(
                                                employeeWorkingHistory))
                        .toList();

        return ResponseEntity.ok(employeeWorkingHistoryGetResponseDTOS);
    }
}
