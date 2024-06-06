package uos.uos25.disposal.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.disposal.dto.request.DisposalCreateReqeustDTO;
import uos.uos25.disposal.dto.response.DisposalCreateResponseDTO;
import uos.uos25.disposal.dto.response.DisposalListGetResponseDTO;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.disposal.service.DisposalService;

@RestController
@RequestMapping("/disposal")
@RequiredArgsConstructor
@Tag(name = "폐기")
public class DisposalController {
    private final DisposalService disposalService;

    @PostMapping
    public ResponseEntity<DisposalCreateResponseDTO> dispose(
            @RequestBody DisposalCreateReqeustDTO disposalCreateReqeustDTO) {
        Disposal disposal = disposalService.createDisposal(disposalCreateReqeustDTO);
        DisposalCreateResponseDTO disposalCreateResponseDTO =
                DisposalCreateResponseDTO.fromEntity(disposal);

        return ResponseEntity.ok(disposalCreateResponseDTO);
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<List<DisposalListGetResponseDTO>> getDisposalsBetweenStartAndEnd(
            @Parameter(example = "1") @PathVariable Long shopId,
            @Parameter(example = "2024-06-01T00:00:00")
                    @RequestParam
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDateTime startDate,
            @Parameter(example = "2024-06-30T23:59:59")
                    @RequestParam
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDateTime endDate) {
        List<Disposal> disposalsWithDate =
                disposalService.findDisposalsWithDate(shopId, startDate, endDate);
        List<DisposalListGetResponseDTO> list =
                disposalsWithDate.stream()
                        .map(disposal -> DisposalListGetResponseDTO.fromEntity(disposal))
                        .toList();

        return ResponseEntity.ok(list);
    }
}
