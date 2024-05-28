package uos.uos25.orders.dto.request;

import lombok.Data;

@Data
public class OrdersStatusRequestDTO {
    private final Long ordersId;
    private final String status;
}
