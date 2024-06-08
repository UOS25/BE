package uos.uos25.orders.dto.request;

import lombok.Data;

@Data
public class OrdersCreateRequestDTO {
    private Long shopId;
    private String barcode;
    private Integer ea;
    private Integer totalPrice;

    public OrdersCreateRequestDTO(Long shopId, String barcode, Integer ea, Integer totalPrice) {
        this.shopId = shopId;
        this.barcode = barcode;
        this.ea = ea;
        this.totalPrice = totalPrice;
    }
}
