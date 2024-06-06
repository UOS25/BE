package uos.uos25.employee.dto.request;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SalaryCalculationRequestDTO {
    @Schema(example = "1")
    private final Long employeeId;

    @Schema(example = "2024-06-08T06:34:11.377Z", description = "년/월 추출해서 사용함")
    private final LocalDateTime date;
}
