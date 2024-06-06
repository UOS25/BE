package uos.uos25.shop.dto;

import lombok.Data;

@Data
public class ShopInfo {
    private final Long shopId;
    private final String shopName;

    public ShopInfo(Long shopId, String shopName) {
        this.shopId = shopId;
        this.shopName = shopName;
    }
}
