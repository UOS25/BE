package uos.uos25.order.dto.response;

import lombok.Builder;
import lombok.Data;
import uos.uos25.headQuarter.dto.ProductInfo;
import uos.uos25.order.entity.Disposal;
import uos.uos25.shop.dto.ShopInfo;

@Data
public class DisposalCreateResponseDTO {
    private final Long disposalId;
    private final Integer ea;
    private final ShopInfo shopInfo;
    private final ProductInfo productInfo;

    @Builder
    public DisposalCreateResponseDTO(
            Long disposalId, Integer ea, ShopInfo shopInfo, ProductInfo productInfo) {
        this.disposalId = disposalId;
        this.ea = ea;
        this.shopInfo = shopInfo;
        this.productInfo = productInfo;
    }

    public static DisposalCreateResponseDTO fromEntity(Disposal disposal) {
        return DisposalCreateResponseDTO.builder()
                .disposalId(disposal.getDisposalId())
                .ea(disposal.getEa())
                .shopInfo(
                        new ShopInfo(
                                disposal.getShop().getShopId(), disposal.getShop().getShopName()))
                .productInfo(
                        new ProductInfo(
                                disposal.getProduct().getBarcode(),
                                disposal.getProduct().getProductName()))
                .build();
    }
}
