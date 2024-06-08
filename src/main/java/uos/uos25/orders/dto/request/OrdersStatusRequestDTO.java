package uos.uos25.orders.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;

@Data
public class OrdersStatusRequestDTO {
    private Long ordersId;

    @JsonCreator
    public OrdersStatusRequestDTO(Long ordersId) {
        this.ordersId = ordersId;
    }
}
