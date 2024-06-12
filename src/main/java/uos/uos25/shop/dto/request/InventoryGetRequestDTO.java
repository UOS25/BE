package uos.uos25.shop.dto.request;

import lombok.Data;

@Data
public class InventoryGetRequestDTO {
    private final Long shopId;
    private final Long productId;
}
