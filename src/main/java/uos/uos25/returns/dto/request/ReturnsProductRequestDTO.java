package uos.uos25.returns.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReturnsProductRequestDTO {
    @Schema(example = "barcode")
    private final String barcode;

    @Schema(example = "2")
    private final Integer ea;
}
