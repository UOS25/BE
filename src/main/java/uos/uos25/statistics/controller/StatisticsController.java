package uos.uos25.statistics.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.statistics.dto.response.StatisticsAgeGetResponseDTO;
import uos.uos25.statistics.dto.response.StatisticsGenderGetResponseDTO;
import uos.uos25.statistics.dto.response.StatisticsSalesGetResponseDTO;
import uos.uos25.statistics.service.StatisticsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
@Tag(name = "통계")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/{shopId}/sales")
    public ResponseEntity<StatisticsSalesGetResponseDTO> getStatisticsSalesByShop(
            @Parameter(example = "1") @PathVariable Long shopId,
            @Parameter(example = "2024-06-01T00:00:00")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @RequestParam
                    LocalDateTime startDate,
            @Parameter(example = "2024-06-30T23:59:59")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @RequestParam
                    LocalDateTime endDate) {
        StatisticsSalesGetResponseDTO statisticsSalesGetResponseDTO =
                statisticsService.findSalesByShopId(shopId, startDate, endDate);

        return ResponseEntity.ok(statisticsSalesGetResponseDTO);
    }

    @GetMapping("/{shopId}/gender")
    public ResponseEntity<StatisticsGenderGetResponseDTO> getStatisticsGenderByShop(
            @Parameter(example = "1") @PathVariable Long shopId,
            @Parameter(example = "2024-06-01T00:00:00")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @RequestParam
                    LocalDateTime startDate,
            @Parameter(example = "2024-06-30T23:59:59")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @RequestParam
                    LocalDateTime endDate) {
        StatisticsGenderGetResponseDTO statisticsGenderGetResponseDTO =
                statisticsService.findGenderByShopId(shopId, startDate, endDate);

        return ResponseEntity.ok(statisticsGenderGetResponseDTO);
    }

    @GetMapping("/{shopId}/age")
    public ResponseEntity<StatisticsAgeGetResponseDTO> getStatisticsAgeByShop(
            @Parameter(example = "1") @PathVariable Long shopId,
            @Parameter(example = "2024-06-01T00:00:00")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @RequestParam
                    LocalDateTime startDate,
            @Parameter(example = "2024-06-30T23:59:59")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @RequestParam
                    LocalDateTime endDate) {
        StatisticsAgeGetResponseDTO statisticsAgeGetResponseDTO =
                statisticsService.findAgeByShopId(shopId, startDate, endDate);

        return ResponseEntity.ok(statisticsAgeGetResponseDTO);
    }
}
