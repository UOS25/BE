package uos.uos25.orders.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.uos25.inventory.service.InventoryService;
import uos.uos25.orders.dto.request.OrdersCreateRequestDTO;
import uos.uos25.orders.dto.request.OrdersUpdateRequestDTO;
import uos.uos25.orders.entity.Orders;
import uos.uos25.orders.entity.OrdersStatus;
import uos.uos25.orders.exception.OrdersNotDelivering;
import uos.uos25.orders.exception.OrdersNotFoundException;
import uos.uos25.orders.exception.OrdersNotRequested;
import uos.uos25.orders.repository.OrdersRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ShopService shopService;
    private final ProductService productService;
    private final InventoryService inventoryService;

    public Orders order(OrdersCreateRequestDTO ordersCreateRequestDTO) {
        Shop shop = shopService.findShopById(ordersCreateRequestDTO.getShopId());
        Product product = productService.findById(ordersCreateRequestDTO.getBarcode());

        Orders orders =
                Orders.builder()
                        .ordersStatus(OrdersStatus.REQUEST.getStatus())
                        .givenEa(0)
                        .ordersEa(ordersCreateRequestDTO.getEa())
                        .shop(shop)
                        .product(product)
                        .build();

        // 재고 생성
        inventoryService.save(
                ordersCreateRequestDTO.getShopId(),
                ordersCreateRequestDTO.getBarcode(),
                ordersCreateRequestDTO.getEa());

        // 주문 생성
        return ordersRepository.save(orders);
    }

    public List<Orders> findAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders findOrdersById(Long ordersId) {
        return ordersRepository.findById(ordersId).orElseThrow(() -> new OrdersNotFoundException());
    }

    @Transactional
    public void updateOrders(OrdersUpdateRequestDTO ordersUpdateRequestDTO) {
        Orders orders =
                ordersRepository
                        .findById(ordersUpdateRequestDTO.getOrdersId())
                        .orElseThrow(() -> new OrdersNotFoundException());
        orders.setOrdersEa(ordersUpdateRequestDTO.getEa());
    }

    @Transactional
    public Long deliveryOrders(Long ordersId) {
        Orders orders =
                ordersRepository
                        .findById(ordersId)
                        .orElseThrow(() -> new OrdersNotFoundException());

        if (!orders.getOrdersStatus().equals(OrdersStatus.REQUEST.getStatus()))
            throw new OrdersNotRequested();

        orders.setOrdersStatus(OrdersStatus.DELIVERED.getStatus());

        return orders.getOrdersId();
    }

    @Transactional
    public Long checkOrders(Long ordersId) {
        Orders orders =
                ordersRepository
                        .findById(ordersId)
                        .orElseThrow(() -> new OrdersNotFoundException());

        if (!orders.getOrdersStatus().equals(OrdersStatus.DELIVERED.getStatus()))
            throw new OrdersNotDelivering();

        orders.setOrdersStatus(OrdersStatus.STORED.getStatus());
        orders.setGivenEa(orders.getOrdersEa());

        // 재고에 ordersEa 추가
        Long shopId = orders.getShop().getShopId();
        String barcode = orders.getProduct().getBarcode();
        inventoryService.addInventory(shopId, barcode, orders.getOrdersEa());

        return orders.getOrdersId();
    }

    public List<Orders> findAllOrdersByShopId(Long shopId) {
        return ordersRepository.findAllByShopShopId(shopId);
    }

    @Transactional
    public void returnOrders(Long ordersId) {
        Orders orders = findOrdersById(ordersId);

        orders.validateCanBeCanceled();
        orders.setOrdersStatus(OrdersStatus.REFUNDED.getStatus());
    }
}
