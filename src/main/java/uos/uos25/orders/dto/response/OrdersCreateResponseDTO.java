package uos.uos25.orders.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import uos.uos25.orders.entity.Orders;
import uos.uos25.product.dto.ProductInfo;
import uos.uos25.shop.dto.ShopInfo;

@Data
public class OrdersCreateResponseDTO {
    private Long ordersId;
    private String ordersStatus;
    private String ordersCheck;
    private Integer givenEa;
    private Integer ordersEa;
    private LocalDateTime createAt;
    private ShopInfo shopInfo;
    private ProductInfo productInfo;

    @Builder
    public OrdersCreateResponseDTO(
            Long ordersId,
            String ordersStatus,
            Integer givenEa,
            Integer ordersEa,
            String ordersCheck,
            LocalDateTime createAt,
            Long shopId,
            String shopName,
            String barcode,
            String productName) {
        this.ordersId = ordersId;
        this.ordersStatus = ordersStatus;
        this.givenEa = givenEa;
        this.ordersEa = ordersEa;
        this.ordersCheck = ordersCheck;
        this.createAt = createAt;
        this.shopInfo = new ShopInfo(shopId, shopName);
        this.productInfo = new ProductInfo(barcode, productName);
    }

    public static OrdersCreateResponseDTO fromEntity(Orders orders) {
        return OrdersCreateResponseDTO.builder()
                .ordersId(orders.getOrdersId())
                .ordersStatus(orders.getOrdersStatus())
                .ordersCheck(orders.getOrdersCheck())
                .givenEa(orders.getGivenEa())
                .ordersEa(orders.getOrdersEa())
                .createAt(orders.getCreatedAt())
                .shopId(orders.getShop().getShopId())
                .shopName(orders.getShop().getShopName())
                .barcode(orders.getProduct().getBarcode())
                .productName(orders.getProduct().getProductName())
                .build();
    }
}
