package uos.uos25.shop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InventoryChangeDisplayRequestDTO {
    @Schema(example = "1")
    private final Long shopId;

    @Schema(example = "barcode")
    private final String barcode;

    @Schema(example = "2")
    private final Integer ea;
}
