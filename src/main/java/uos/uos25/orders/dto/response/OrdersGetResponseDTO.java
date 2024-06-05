package uos.uos25.orders.dto.response;

import lombok.Builder;
import lombok.Data;
import uos.uos25.orders.entity.Orders;
import uos.uos25.product.dto.ProductInfo;
import uos.uos25.shop.dto.ShopInfo;

@Data
public class OrdersGetResponseDTO {
    private Long ordersId;
    private String ordersStatus;
    private String ordersCheck;
    private Integer givenEa;
    private Integer ordersEa;
    private ShopInfo shopInfo;
    private ProductInfo productInfo;

    @Builder
    public OrdersGetResponseDTO(
            Long ordersId,
            String ordersStatus,
            Integer givenEa,
            Integer ordersEa,
            String ordersCheck,
            Long shopId,
            String shopName,
            String barcode,
            String productName) {
        this.ordersId = ordersId;
        this.ordersStatus = ordersStatus;
        this.givenEa = givenEa;
        this.ordersEa = ordersEa;
        this.ordersCheck = ordersCheck;
        this.shopInfo = new ShopInfo(shopId, shopName);
        this.productInfo = new ProductInfo(barcode, productName);
    }

    public static OrdersGetResponseDTO fromEntity(Orders orders) {
        return OrdersGetResponseDTO.builder()
                .ordersId(orders.getOrdersId())
                .ordersStatus(orders.getOrdersStatus())
                .ordersCheck(orders.getOrdersCheck())
                .givenEa(orders.getGivenEa())
                .ordersEa(orders.getOrdersEa())
                .shopId(orders.getShop().getShopId())
                .shopName(orders.getShop().getShopName())
                .barcode(orders.getProduct().getBarcode())
                .productName(orders.getProduct().getProductName())
                .build();
    }
}

// {
//		orderNo: int,
//		productId: int,
//		productName: str,
//		price: int,
//		ea: int,
//		description: str
//	},
