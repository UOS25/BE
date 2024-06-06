package uos.uos25.inventory.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uos.uos25.inventory.entity.Inventory;

@Data
@NoArgsConstructor
public class InventoryGetResponseDTO {
    private String barcode;
    private String productName;
    private Integer ea;
    private Integer displayEa;

    @Builder
    public InventoryGetResponseDTO(
            String barcode, String productName, Integer ea, Integer displayEa) {
        this.barcode = barcode;
        this.productName = productName;
        this.ea = ea;
        this.displayEa = displayEa;
    }

    public static InventoryGetResponseDTO fromEntity(Inventory inventory) {
        InventoryGetResponseDTO inventoryGetResponseDTO =
                InventoryGetResponseDTO.builder()
                        .barcode(inventory.getProduct().getBarcode())
                        .productName(inventory.getProduct().getProductName())
                        .ea(inventory.getEa())
                        .displayEa(inventory.getDisplay())
                        .build();

        return inventoryGetResponseDTO;
    }
}
