package uos.uos25.shop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.shop.dto.request.DisbursementCreateRequestDTO;
import uos.uos25.shop.dto.request.SalaryCalculationRequestDTO;
import uos.uos25.shop.dto.response.DisbursementGetResponseDTO;
import uos.uos25.shop.entity.Disbursement;
import uos.uos25.shop.service.DisbursementService;

@Controller
@RequestMapping("/disburse")
@RequiredArgsConstructor
@Tag(name = "자금출납")
public class DisbursementController {

    private final DisbursementService disbursementService;

    @Operation(summary = "전체 조회")
    @GetMapping
    public ResponseEntity<List<DisbursementGetResponseDTO>> getAll() {
        List<Disbursement> disbursements = disbursementService.findAll();
        List<DisbursementGetResponseDTO> disbursementGetResponseDTOS =
                disbursements.stream()
                        .map(disbursement -> DisbursementGetResponseDTO.fromEntity(disbursement))
                        .toList();

        return ResponseEntity.ok(disbursementGetResponseDTOS);
    }

    @Operation(summary = "지점별 조회")
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<DisbursementGetResponseDTO>> getByShopId(
            @Parameter(example = "1") @PathVariable Long shopId) {
        List<Disbursement> disbursements = disbursementService.findAllByShopId(shopId);
        List<DisbursementGetResponseDTO> disbursementGetResponseDTOS =
                disbursements.stream()
                        .map(disbursement -> DisbursementGetResponseDTO.fromEntity(disbursement))
                        .toList();

        return ResponseEntity.ok(disbursementGetResponseDTOS);
    }

    @Operation(summary = "직원별 조회")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<DisbursementGetResponseDTO>> getByEmployeeId(
            @Parameter(example = "1") @PathVariable Long employeeId) {
        List<Disbursement> disbursements = disbursementService.findAllByEmployeeId(employeeId);
        List<DisbursementGetResponseDTO> disbursementGetResponseDTOS =
                disbursements.stream()
                        .map(disbursement -> DisbursementGetResponseDTO.fromEntity(disbursement))
                        .toList();

        return ResponseEntity.ok(disbursementGetResponseDTOS);
    }

    @Operation(summary = "직원 급여 정산", description = "급여 정산되지 않은 달만 사용 가능")
    @PostMapping("/salary-calculation")
    public ResponseEntity<DisbursementGetResponseDTO> calculateSalary(
            @RequestBody SalaryCalculationRequestDTO salaryCalculationRequestDTO) {
        Disbursement disbursement =
                disbursementService.calculateSalary(salaryCalculationRequestDTO);
        DisbursementGetResponseDTO disbursementGetResponseDTO =
                DisbursementGetResponseDTO.fromEntity(disbursement);

        return ResponseEntity.ok(disbursementGetResponseDTO);
    }

    @Operation(summary = "본사로 자금 출납")
    @PostMapping
    public ResponseEntity<DisbursementGetResponseDTO> disburseToHeadquarter(
            @RequestBody DisbursementCreateRequestDTO disbursementCreateRequestDTO) {
        Disbursement disburse = disbursementService.disburse(disbursementCreateRequestDTO);
        DisbursementGetResponseDTO disbursementGetResponseDTO =
                DisbursementGetResponseDTO.fromEntity(disburse);

        return ResponseEntity.ok(disbursementGetResponseDTO);
    }
}
