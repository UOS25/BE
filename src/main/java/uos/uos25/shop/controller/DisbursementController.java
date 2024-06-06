package uos.uos25.shop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.shop.dto.response.DisbursementGetResponseDTO;
import uos.uos25.shop.entity.Disbursement;
import uos.uos25.shop.service.DisbursementService;

@Controller
@RequestMapping("/disbursement")
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
    @GetMapping("/{shopId}")
    public ResponseEntity<List<DisbursementGetResponseDTO>> getByShopId(
            @Parameter(example = "1") @PathVariable Long shopId) {
        List<Disbursement> disbursements = disbursementService.findAllByShopId(shopId);
        List<DisbursementGetResponseDTO> disbursementGetResponseDTOS =
                disbursements.stream()
                        .map(disbursement -> DisbursementGetResponseDTO.fromEntity(disbursement))
                        .toList();

        return ResponseEntity.ok(disbursementGetResponseDTOS);
    }
}
