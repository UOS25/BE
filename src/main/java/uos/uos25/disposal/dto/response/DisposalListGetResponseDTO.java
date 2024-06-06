package uos.uos25.disposal.dto.response;

import lombok.Builder;
import lombok.Data;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.product.dto.ProductInfo;
import uos.uos25.shop.dto.ShopInfo;

@Data
public class DisposalListGetResponseDTO {
    private final Long disposalId;
    private final Integer ea;
    private final ShopInfo shopInfo;
    private final ProductInfo productInfo;

    @Builder
    public DisposalListGetResponseDTO(
            Long disposalId, Integer ea, ShopInfo shopInfo, ProductInfo productInfo) {
        this.disposalId = disposalId;
        this.ea = ea;
        this.shopInfo = shopInfo;
        this.productInfo = productInfo;
    }

    public static DisposalListGetResponseDTO fromEntity(Disposal disposal) {
        return DisposalListGetResponseDTO.builder()
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
