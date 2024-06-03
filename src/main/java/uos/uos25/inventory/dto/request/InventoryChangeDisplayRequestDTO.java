package uos.uos25.inventory.dto.request;

import lombok.Data;

@Data
public class InventoryChangeDisplayRequestDTO {
    private final Long shopId;
    private final Long productId;
    private final Integer ea;
}
