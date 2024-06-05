package uos.uos25.inventory.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class InventoryGetListResponseDTO {
    private List<InventoryGetResponseDTO> inventoryGetResponseDTOS;
}
