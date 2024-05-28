package uos.uos25.orders.dto.request;

import lombok.Data;

@Data
public class OrdersRequestDTO {
    private Long productId;
    private Long shopId;
    private String productName;
    private Integer ea;
    private Integer totalPrice;
}
