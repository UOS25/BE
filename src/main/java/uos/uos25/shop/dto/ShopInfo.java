package uos.uos25.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopInfo {
    private final Long shopId;
    private final String shopName;
}
