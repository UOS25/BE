package uos.uos25.returns.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uos.uos25.product.dto.ProductInfo;
import uos.uos25.returns.entity.Returns;
import uos.uos25.shop.dto.ShopInfo;

@Data
@NoArgsConstructor
public class ReturnsGetResponseDTO {
    private Long returnsId;
    private Integer ea;
    private String returnsStatus;
    private LocalDateTime createAt;
    private ShopInfo shopInfo;
    private ProductInfo productInfo;

    @Builder
    public ReturnsGetResponseDTO(
            Long returnsId,
            Integer ea,
            String returnsStatus,
            LocalDateTime createAt,
            ShopInfo shopInfo,
            ProductInfo productInfo) {
        this.returnsId = returnsId;
        this.ea = ea;
        this.returnsStatus = returnsStatus;
        this.createAt = createAt;
        this.shopInfo = shopInfo;
        this.productInfo = productInfo;
    }

    public static ReturnsGetResponseDTO fromEntity(Returns returns) {
        return ReturnsGetResponseDTO.builder()
                .returnsId(returns.getReturnsId())
                .ea(returns.getEa())
                .returnsStatus(returns.getReturnsStatus())
                .createAt(returns.getCreatedAt())
                .shopInfo(
                        new ShopInfo(
                                returns.getShop().getShopId(), returns.getShop().getShopName()))
                .productInfo(
                        new ProductInfo(
                                returns.getProduct().getBarcode(),
                                returns.getProduct().getProductName()))
                .build();
    }
}
