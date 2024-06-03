package uos.uos25.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.inventory.dto.request.InventoryChangeDisplayRequestDTO;
import uos.uos25.inventory.dto.response.InventoryGetResponseDTO;
import uos.uos25.inventory.service.InventoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @PatchMapping
    public ResponseEntity<Void> changeProductToDisplay(@RequestBody InventoryChangeDisplayRequestDTO displayRequestDTO){
        Long shopId = displayRequestDTO.getShopId();
        Long productId = displayRequestDTO.getProductId();
        Integer ea = displayRequestDTO.getEa();

        inventoryService.displayInventory(shopId, productId, ea);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<List<InventoryGetResponseDTO>> getInventoryByShopId(@PathVariable Long shopId){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getInventoriesByShopId(shopId));
    }

    @GetMapping("/{shopId}/{productId}")
    public ResponseEntity<InventoryGetResponseDTO> getInventoryByShopIdAndProductId(
            @PathVariable Long shopId,
            @PathVariable Long productId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.getInventoryByShopIdAndProductId(shopId, productId));
    }
}
