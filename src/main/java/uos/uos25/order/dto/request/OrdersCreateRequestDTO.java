package uos.uos25.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrdersCreateRequestDTO {
    @Schema(example = "1")
    private Long shopId;

    @Schema(example = "barcode")
    private String barcode;

    @Schema(example = "10")
    private Integer ea;

    public OrdersCreateRequestDTO(Long shopId, String barcode, Integer ea) {
        this.shopId = shopId;
        this.barcode = barcode;
        this.ea = ea;
    }
}
