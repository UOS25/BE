package uos.uos25.statistics.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.statistics.dto.response.StatisticsGetResponseDTO;
import uos.uos25.statistics.service.StatisticsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
@Tag(name = "통계")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/{shopId}")
    public ResponseEntity<StatisticsGetResponseDTO> getStatisticsByShop(
            @Parameter(example = "1") @PathVariable Long shopId,
            @Parameter(example = "2024-06-01T00:00:00")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @RequestParam
                    LocalDateTime startDate,
            @Parameter(example = "2024-06-30T23:59:59")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @RequestParam
                    LocalDateTime endDate) {
        StatisticsGetResponseDTO statisticsGetResponseDTO =
                statisticsService.findByShopId(shopId, startDate, endDate);

        return ResponseEntity.ok(statisticsGetResponseDTO);
    }
}
