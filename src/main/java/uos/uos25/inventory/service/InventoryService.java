package uos.uos25.inventory.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.inventory.exception.InventoryNotFoundException;
import uos.uos25.inventory.repository.InventoryRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ShopService shopService;
    private final ProductService productService;

    @Transactional
    public Inventory save(Long shopId, String barcode, Integer ea) {
        Shop shop = shopService.findShopById(shopId);
        Product product = productService.findById(barcode);

        Inventory inventory =
                inventoryRepository
                        .findByShopShopIdAndProductBarcode(shopId, barcode)
                        .orElseGet(
                                () -> {
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

    public List<Inventory> findInventoriesByShopId(Long shopId) {
        Shop shop = shopService.findShopById(shopId);

        List<Inventory> inventories =
                inventoryRepository
                        .findAllByShopShopId(shopId)
                        .orElseThrow(
                                () -> {
                                    throw new InventoryNotFoundException();
                                });

        return inventories;
    }

    public Inventory findInventoryByShopIdAndBarcode(Long shopId, String barcode) {
        Inventory inventory =
                inventoryRepository
                        .findByShopShopIdAndProductBarcode(shopId, barcode)
                        .orElseThrow(
                                () -> {
                                    throw new InventoryNotFoundException();
                                });

        return inventory;
    }

    @Transactional
    public void displayInventory(Long shopId, String barcode, Integer ea) {
        Inventory inventory =
                inventoryRepository
                        .findByShopShopIdAndProductBarcode(shopId, barcode)
                        .orElseThrow(
                                () -> {
                                    throw new InventoryNotFoundException();
                                });

        inventory.display(ea);
    }

    public Inventory findInventoryByShopIdAndProductName(Long shopId, String productName) {
        return inventoryRepository
                .findByShopShopIdAndProductProductName(shopId, productName)
                .orElseThrow(() -> new InventoryNotFoundException());
    }

    @Transactional
    public void addInventory(Long shopId, String barcode, Integer ea) {
        Shop shop = shopService.findShopById(shopId);
        Product product = productService.findById(barcode);

        Inventory inventory =
                inventoryRepository
                        .findByShopShopIdAndProductBarcode(shopId, barcode)
                        .orElseGet(
                                () -> {
                                    Inventory newInventory =
                                            Inventory.builder()
                                                    .shop(shop)
                                                    .product(product)
                                                    .ea(ea)
                                                    .display(0)
                                                    .warehousingDate(LocalDateTime.now())
                                                    .expirationDate(
                                                            LocalDateTime.now().plusDays(10L))
                                                    .build();
                                    return inventoryRepository.save(newInventory);
                                });
        inventory.addEa(ea);
    }
}
