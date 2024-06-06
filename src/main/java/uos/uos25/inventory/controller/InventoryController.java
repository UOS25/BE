package uos.uos25.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.inventory.dto.request.InventoryChangeDisplayRequestDTO;
import uos.uos25.inventory.dto.response.InventoryGetResponseDTO;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.inventory.service.InventoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
@Tag(name = "재고")
public class InventoryController {
    private final InventoryService inventoryService;

    @Operation(summary = "물품 진열")
    @PatchMapping
    public ResponseEntity<Void> changeProductToDisplay(
            @RequestBody InventoryChangeDisplayRequestDTO displayRequestDTO) {
        Long shopId = displayRequestDTO.getShopId();
        String barcode = displayRequestDTO.getBarcode();
        Integer ea = displayRequestDTO.getEa();

        inventoryService.displayInventory(shopId, barcode, ea);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<List<InventoryGetResponseDTO>> getInventoryByShopId(
            @Parameter(example = "1") @PathVariable Long shopId) {
        List<Inventory> inventoriesByShopId = inventoryService.findInventoriesByShopId(shopId);
        List<InventoryGetResponseDTO> inventoryGetResponseDTOS =
                inventoriesByShopId.stream()
                        .map(inventory -> InventoryGetResponseDTO.fromEntity(inventory))
                        .toList();

        return ResponseEntity.ok(inventoryGetResponseDTOS);
    }

    @GetMapping("/{shopId}/{barcode}")
    public ResponseEntity<InventoryGetResponseDTO> getInventoryByShopIdAndProductId(
            @Parameter(example = "1") @PathVariable Long shopId,
            @Parameter(example = "barcode") @PathVariable String barcode) {
        Inventory inventory = inventoryService.findInventoryByShopIdAndProductId(shopId, barcode);

        return ResponseEntity.ok(InventoryGetResponseDTO.fromEntity(inventory));
    }
}
