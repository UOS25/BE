package uos.uos25.inventory.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uos.uos25.inventory.entity.Inventory;

@Data
@NoArgsConstructor
public class InventoryGetResponseDTO {
    private Long shopId;
    private Long productId;
    private String productName;
    private Integer ea;
    private Integer displayEa;

    @Builder
    public InventoryGetResponseDTO(Long shopId, Long productId, String productName, Integer ea, Integer displayEa) {
        this.shopId = shopId;
        this.productId = productId;
        this.productName = productName;
        this.ea = ea;
        this.displayEa = displayEa;
    }
}
