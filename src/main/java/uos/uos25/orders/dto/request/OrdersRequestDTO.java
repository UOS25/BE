package uos.uos25.orders.dto.request;

import lombok.Data;

@Data
public class OrdersRequestDTO {
    private Long productId;
    private Long shopId;
    private String productName;
    private Integer ea;
    private Integer totalPrice;

    public OrdersRequestDTO(Long productId, Long shopId, String productName, Integer ea, Integer totalPrice) {
        this.productId = productId;
        this.shopId = shopId;
        this.productName = productName;
        this.ea = ea;
        this.totalPrice = totalPrice;
    }
}
