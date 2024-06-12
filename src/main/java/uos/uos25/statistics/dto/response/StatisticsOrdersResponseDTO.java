package uos.uos25.statistics.dto.response;

import lombok.Data;
import uos.uos25.order.dto.response.OrdersGetResponseDTO;

@Data
public class StatisticsOrdersResponseDTO {
    private final OrdersGetResponseDTO orders;
    private final Integer price;
}
