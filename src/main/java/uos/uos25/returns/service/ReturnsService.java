package uos.uos25.returns.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.inventory.service.InventoryService;
import uos.uos25.orders.service.OrdersService;
import uos.uos25.returns.entity.Returns;
import uos.uos25.returns.exception.ReturnsNotFoundException;
import uos.uos25.returns.repository.ReturnsRepository;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

@Service
@RequiredArgsConstructor
public class ReturnsService {
    private final ReturnsRepository returnsRepository;
    private final ShopService shopService;
    private final OrdersService ordersService;
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
}
