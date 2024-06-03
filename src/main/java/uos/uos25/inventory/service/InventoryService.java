package uos.uos25.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.uos25.inventory.dto.response.InventoryGetResponseDTO;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.inventory.exception.InventoryNotFoundException;
import uos.uos25.inventory.repository.InventoryRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ShopService shopService;
    private final ProductService productService;

    @Transactional
    public Inventory save(Long shopId, Long productId, Integer ea){
        Shop shop = shopService.findShopById(shopId);
        Product product = productService.findProductById(productId);

        Inventory inventory = inventoryRepository.findByShopShopIdAndProductProductId(shopId, productId)
                .orElseGet(() -> {
                    return Inventory.builder()
                            .shop(shop)
                            .product(product)
                            .ea(ea)
                            .display(0)
                            .warehousingDate(LocalDateTime.now())
                            .expirationDate(LocalDateTime.now().plusDays(10L))
                            .build();
                });

        return inventoryRepository.save(inventory);
    }

    public List<InventoryGetResponseDTO> getInventoriesByShopId(Long shopId){
        Shop shop = shopService.findShopById(shopId);

        List<Inventory> inventories = inventoryRepository.findAllByShopShopId(shopId).orElseThrow(() -> {
            throw new InventoryNotFoundException();
        });

        List<InventoryGetResponseDTO> inventoryGetResponseDTOS = inventories.stream()
                .map(this::fromEntity)
                .toList();

        return inventoryGetResponseDTOS;
    }

    public InventoryGetResponseDTO getInventoryByShopIdAndProductId(Long shopId, Long productId){
        Shop shop = shopService.findShopById(shopId);
        Product product = productService.findProductById(productId);

        Inventory inventory = inventoryRepository.findByShopShopIdAndProductProductId(shopId, productId)
                .orElseThrow(() -> {
                    throw new InventoryNotFoundException();
                });

        InventoryGetResponseDTO inventoryGetResponseDTO = fromEntity(inventory);
        return inventoryGetResponseDTO;
    }

    @Transactional
    public void displayInventory(Long shopId, Long productId, Integer ea){
        Shop shop = shopService.findShopById(shopId);
        Product product = productService.findProductById(productId);
        Inventory inventory = inventoryRepository.findByShopShopIdAndProductProductId(shopId, productId)
                .orElseThrow(() -> {throw new InventoryNotFoundException();});

        inventory.changeDisplay(ea);
    }

    private InventoryGetResponseDTO fromEntity(Inventory inventory){
        InventoryGetResponseDTO inventoryGetResponseDTO = InventoryGetResponseDTO.builder()
                .shopId(inventory.getShop().getShopId())
                .productId(inventory.getProduct().getProductId())
                .productName(inventory.getProduct().getProductName())
                .ea(inventory.getEa())
                .displayEa(inventory.getDisplay())
                .build();

        return inventoryGetResponseDTO;
    }
}
