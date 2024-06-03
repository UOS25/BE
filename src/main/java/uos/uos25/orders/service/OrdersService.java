package uos.uos25.orders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.uos25.inventory.service.InventoryService;
import uos.uos25.orders.dto.request.OrdersModifyRequestDTO;
import uos.uos25.orders.dto.request.OrdersRequestDTO;
import uos.uos25.orders.dto.request.OrdersStatusRequestDTO;
import uos.uos25.orders.entity.Orders;
import uos.uos25.orders.exception.OrdersNotFoundException;
import uos.uos25.orders.repository.OrdersRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ShopService shopService;
    private final ProductService productService;
    private final InventoryService inventoryService;

    public Orders save(OrdersRequestDTO ordersRequestDTO){
        Shop shop = shopService.findShopById(ordersRequestDTO.getShopId());
        Product product = productService.findProductById(ordersRequestDTO.getProductId());

        Orders orders = Orders.builder()
                .ordersStatus("뭘로 할까")
                .givenEa(0)
                .ordersEa(ordersRequestDTO.getEa())
                .ordersCheck("뭘로 하지")
                .shop(shop)
                .product(product)
                .build();

        // 재고 생성
        inventoryService.save(ordersRequestDTO.getShopId(), ordersRequestDTO.getProductId(), ordersRequestDTO.getEa());

        // 주문 생성
        return ordersRepository.save(orders);
    }

    public List<Orders> findOrders(){
        return ordersRepository.findAll();
    }

    @Transactional
    public Long updateOrders(OrdersModifyRequestDTO ordersModifyRequestDTO){
        Orders orders = ordersRepository.findById(ordersModifyRequestDTO.getOrdersId()).orElseThrow(() -> new OrdersNotFoundException());
        orders.setOrdersEa(ordersModifyRequestDTO.getEa());

        return orders.getOrdersId();
    }

    @Transactional
    public void deleteOrdersById(Long ordersId){
        ordersRepository.deleteById(ordersId);
    }

    @Transactional
    public Long modifyOrdersStatus(OrdersStatusRequestDTO ordersStatusRequestDTO){
        Orders orders = ordersRepository.findById(ordersStatusRequestDTO.getOrdersId()).orElseThrow(() -> new OrdersNotFoundException());
        orders.setOrdersStatus(ordersStatusRequestDTO.getStatus());

        return orders.getOrdersId();
    }

}
