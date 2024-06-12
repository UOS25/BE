package uos.uos25.shop.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class InventoryGetListResponseDTO {
    private List<InventoryGetResponseDTO> inventoryGetResponseDTOS;
}
