package uos.uos25.shop.dto.request;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DisbursementCreateRequestDTO {
    @Schema(example = "1")
    private final Long shopId;

    @Schema(example = "5000000")
    private final Integer disburseAmount;

    @Schema(example = "로열티")
    private final String disburseType;

    @Schema(example = "2024-06-08T06:34:11.377Z", description = "년/월 추출해서 사용함")
    private final LocalDateTime date;
}
