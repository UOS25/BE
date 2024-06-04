package uos.uos25.inventory.dto.request;

import lombok.Data;

@Data
public class InventoryChangeDisplayRequestDTO {
    private final Long shopId;
    private final String barcode;
    private final Integer ea;
}
