package uos.uos25.inventory.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class InventoryGetListResponseDTO {
    private List<InventoryGetResponseDTO> inventoryGetResponseDTOS;
}
