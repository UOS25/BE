package uos.uos25.orders.dto.request;

import lombok.Data;

@Data
public class OrdersRequestDTO {
    private Long shopId;
    private String barcode;
    private String productName;
    private Integer ea;
    private Integer totalPrice;

    public OrdersRequestDTO(
            Long shopId, String barcode, String productName, Integer ea, Integer totalPrice) {
        this.shopId = shopId;
        this.barcode = barcode;
        this.productName = productName;
        this.ea = ea;
        this.totalPrice = totalPrice;
    }
}
