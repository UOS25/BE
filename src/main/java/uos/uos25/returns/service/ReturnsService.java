package uos.uos25.returns.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.inventory.service.InventoryService;
import uos.uos25.orders.entity.Orders;
import uos.uos25.orders.entity.OrdersStatus;
import uos.uos25.orders.exception.OrdersCannotBeCanceled;
import uos.uos25.orders.service.OrdersService;
import uos.uos25.product.entity.Product;
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
    private final OrdersService ordersService;
    private final InventoryService inventoryService;

    public void returnOrders(Long ordersId) {
        Orders orders = ordersService.findOrdersById(ordersId);

        if (!orders.canBeCanceled()) throw new OrdersCannotBeCanceled();
        orders.setOrdersStatus(OrdersStatus.REFUNDED.getStatus());

        Shop shop = orders.getShop();
        Product product = orders.getProduct();

        Returns returns =
                Returns.builder()
                        .shop(shop)
                        .product(product)
                        .ea(orders.getOrdersEa())
                        .returnsStatus(ReturnsStatus.REFUNDED.getStatus())
                        .build();
        returnsRepository.save(returns);

        // 재고에서 제거
        inventoryService.decreaseInventory(
                shop.getShopId(), product.getBarcode(), orders.getOrdersEa());
    }

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
