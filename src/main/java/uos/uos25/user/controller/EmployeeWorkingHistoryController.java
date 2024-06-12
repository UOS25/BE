package uos.uos25.user.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.user.dto.EmployeeWorkingHistoryDTO;
import uos.uos25.user.dto.response.EmployeeWorkingHistoryGetResponseDTO;
import uos.uos25.user.entity.EmployeeWorkingHistory;
import uos.uos25.user.repository.EmployeeRepository;
import uos.uos25.user.service.EmployeeWorkingHistoryService;

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
    public ResponseEntity<Void> startWorking(@PathVariable Long employeeId) {
        // service에서 startWorking을 호출합니다.
        employeeWorkingHistoryService.startWorking(employeeId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 직원의 퇴근 처리
    @PutMapping("/{employeeId}/end")
    public ResponseEntity<Void> endWorking(@PathVariable Long employeeId) {
        // service에서 endWorking을 호출합니다.
        employeeWorkingHistoryService.endWorking(employeeId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 임의로 직원의 출퇴근 기록을 추가
    @PostMapping("/addHistory")
    public ResponseEntity<Void> addWorkingHistory(
            @Valid @RequestBody EmployeeWorkingHistoryDTO employeeWorkingHistoryDTO) {
        // service에서 addWorkingHistory를 호출합니다.
        employeeWorkingHistoryService.addWorkingHistory(employeeWorkingHistoryDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 직원의 출퇴근 기록을 삭제
    @DeleteMapping("/delete/{employeeWorkingHistoryId}")
    public ResponseEntity<Void> deleteWorkingHistory(@PathVariable Long employeeWorkingHistoryId) {
        employeeWorkingHistoryService.deleteEmployeeWorkingHistory(employeeWorkingHistoryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
