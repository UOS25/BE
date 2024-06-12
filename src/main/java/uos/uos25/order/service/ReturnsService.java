package uos.uos25.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.uos25.headquarter.entity.Product;
import uos.uos25.headquarter.service.ProductService;
import uos.uos25.order.dto.request.ReturnsCreateRequestDTO;
import uos.uos25.order.dto.request.ReturnsProductRequestDTO;
import uos.uos25.order.entity.Returns;
import uos.uos25.order.entity.ReturnsStatus;
import uos.uos25.order.exception.ReturnsNotFoundException;
import uos.uos25.order.repository.ReturnsRepository;
import uos.uos25.shop.entity.Inventory;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.InventoryService;
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
