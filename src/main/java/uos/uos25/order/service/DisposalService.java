package uos.uos25.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.headQuarter.entity.Product;
import uos.uos25.headQuarter.service.ProductService;
import uos.uos25.order.dto.request.DisposalCreateReqeustDTO;
import uos.uos25.order.entity.Disposal;
import uos.uos25.order.repository.DisposalRepository;
import uos.uos25.shop.entity.Inventory;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.InventoryService;
import uos.uos25.shop.service.ShopService;

@Service
@RequiredArgsConstructor
public class DisposalService {
    private final DisposalRepository disposalRepository;
    private final ProductService productService;
    private final ShopService shopService;
    private final InventoryService inventoryService;

    public Disposal dispose(DisposalCreateReqeustDTO disposalCreateReqeustDTO) {
        Shop shop = shopService.findShopById(disposalCreateReqeustDTO.getShopId());
        Product product = productService.findById(disposalCreateReqeustDTO.getBarcode());

        Disposal disposal =
                Disposal.builder()
                        .shop(shop)
                        .product(product)
                        .ea(disposalCreateReqeustDTO.getEa())
                        .build();

        // 재고에서 제거
        Inventory inventory =
                inventoryService.findInventoryByShopIdAndBarcode(
                        shop.getShopId(), product.getBarcode());
        inventory.subtractEa(disposalCreateReqeustDTO.getEa());

        return disposalRepository.save(disposal);
    }

    public List<Disposal> findDisposalsWithDate(
            Long shopId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Disposal> disposalsWithDate =
                disposalRepository.findByCreatedAtBetweenAndShopShopId(startDate, endDate, shopId);

        return disposalsWithDate;
    }

    public List<Disposal> findByShopId(Long shopId) {
        return disposalRepository.findAllByShopShopId(shopId);
    }
}
