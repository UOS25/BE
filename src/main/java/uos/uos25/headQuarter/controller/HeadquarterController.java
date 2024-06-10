package uos.uos25.headQuarter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.headQuarter.dto.response.HeadquarterGetResponseDTO;
import uos.uos25.headQuarter.entity.HeadQuarter;
import uos.uos25.headQuarter.service.HeadquarterService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/headquarter")
@Tag(name = "본사")
public class HeadquarterController {
    private final HeadquarterService headquarterService;

    @Operation(summary = "본사 직원 조회")
    @GetMapping
    public ResponseEntity<List<HeadquarterGetResponseDTO>> getHeadquarterStaff() {
        List<HeadQuarter> headQuarters = headquarterService.findAll();
        List<HeadquarterGetResponseDTO> headquarterGetResponseDTOS =
                headQuarters.stream()
                        .map(headquarter -> HeadquarterGetResponseDTO.fromEntity(headquarter))
                        .toList();

        return ResponseEntity.ok(headquarterGetResponseDTOS);
    }
}
