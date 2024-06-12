package uos.uos25.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DisposalCreateReqeustDTO {
    @Schema(example = "1")
    private final Long shopId;

    @Schema(example = "barcode")
    private final String barcode;

    @Schema(example = "3")
    private final Integer ea;
}
