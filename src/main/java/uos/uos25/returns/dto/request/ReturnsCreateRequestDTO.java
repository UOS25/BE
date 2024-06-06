package uos.uos25.returns.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReturnsCreateRequestDTO {
    @Schema(example = "1")
    private final Long shopId;

    @Schema(example = "barcode")
    private final String barcode;

    @Schema(example = "10")
    private final Integer ea;
}
