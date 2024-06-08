package uos.uos25.orders.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrdersStatusRequestDTO {
    @Schema(example = "1")
    private Long ordersId;

    @JsonCreator
    public OrdersStatusRequestDTO(Long ordersId) {
        this.ordersId = ordersId;
    }
}
