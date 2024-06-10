package uos.uos25.returns.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.inventory.service.InventoryService;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.returns.dto.request.ReturnsCreateRequestDTO;
import uos.uos25.returns.dto.request.ReturnsProductRequestDTO;
import uos.uos25.returns.entity.Returns;
import uos.uos25.returns.entity.ReturnsStatus;
import uos.uos25.returns.exception.ReturnsNotFoundException;
import uos.uos25.returns.repository.ReturnsRepository;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

@Service
@RequiredArgsConstructor
public class ReturnsService {
    private final ReturnsRepository returnsRepository;
    private final ShopService shopService;
    private final ProductService productService;
    private final InventoryService inventoryService;

    public List<Returns> findAllByShopId(Long shopId) {
        Shop shop = shopService.findShopById(shopId);
        return returnsRepository.findAllByShop_ShopId(shopId);
    }

    public Returns findById(Long returnsId) {
        return returnsRepository
                .findById(returnsId)
                .orElseThrow(() -> new ReturnsNotFoundException());
    }

    @Transactional
    public void returnProduct(ReturnsCreateRequestDTO returnsCreateRequestDTO) {
        Shop shop = shopService.findShopById(returnsCreateRequestDTO.getShopId());

        for (ReturnsProductRequestDTO returnsProductRequestDTO :
                returnsCreateRequestDTO.getProducts()) {
            Product product = productService.findById(returnsProductRequestDTO.getBarcode());
            Integer ea = returnsProductRequestDTO.getEa();

            Returns returns =
                    Returns.builder()
                            .product(product)
                            .shop(shop)
                            .ea(ea)
                            .returnsStatus(ReturnsStatus.REFUNDED.getStatus())
                            .build();
            returnsRepository.save(returns);

            // 재고 상품 제거
            Inventory inventory =
                    inventoryService.findInventoryByShopIdAndBarcode(
                            shop.getShopId(), product.getBarcode());
            inventory.subtractEa(ea);
        }
    }
}
