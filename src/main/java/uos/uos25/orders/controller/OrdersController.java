package uos.uos25.orders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.orders.dto.request.OrdersCreateRequestDTO;
import uos.uos25.orders.dto.request.OrdersStatusRequestDTO;
import uos.uos25.orders.dto.request.OrdersUpdateRequestDTO;
import uos.uos25.orders.dto.response.OrdersCreateResponseDTO;
import uos.uos25.orders.dto.response.OrdersGetResponseDTO;
import uos.uos25.orders.entity.Orders;
import uos.uos25.orders.service.OrdersService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "주문", description = "본사로의")
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<OrdersCreateResponseDTO> order(
            @RequestBody OrdersCreateRequestDTO ordersCreateRequestDTO) {
        Orders savedOrders = ordersService.save(ordersCreateRequestDTO);
        OrdersCreateResponseDTO ordersCreateResponseDTO =
                OrdersCreateResponseDTO.fromEntity(savedOrders);

        return ResponseEntity.ok(ordersCreateResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrdersGetResponseDTO>> getList() {
        List<Orders> orders = ordersService.findAllOrders();
        List<OrdersGetResponseDTO> ordersGetResponseDTOS =
                orders.stream().map(order -> OrdersGetResponseDTO.fromEntity(order)).toList();

        return ResponseEntity.ok(ordersGetResponseDTOS);
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<OrdersGetResponseDTO>> getListByShopId(
            @Parameter(example = "1") @PathVariable Long shopId) {
        List<Orders> orders = ordersService.findAllOrdersByShopId(shopId);
        List<OrdersGetResponseDTO> ordersGetResponseDTOS =
                orders.stream().map(order -> OrdersGetResponseDTO.fromEntity(order)).toList();

        return ResponseEntity.ok(ordersGetResponseDTOS);
    }

    @GetMapping("/{ordersId}")
    public ResponseEntity<OrdersGetResponseDTO> getOrdersById(
            @Parameter(example = "1") @PathVariable Long ordersId) {
        Orders orders = ordersService.findOrdersById(ordersId);
        OrdersGetResponseDTO ordersGetResponseDTO = OrdersGetResponseDTO.fromEntity(orders);

        return ResponseEntity.ok(ordersGetResponseDTO);
    }

    @PatchMapping
    public ResponseEntity<Void> modifyOrders(
            @RequestBody OrdersUpdateRequestDTO ordersUpdateRequestDTO) {
        ordersService.updateOrders(ordersUpdateRequestDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{ordersId}")
    public ResponseEntity<Void> deleteOrders(@PathVariable Long ordersId) {
        ordersService.deleteOrdersById(ordersId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "배송 시작")
    @PatchMapping("/delivery")
    public ResponseEntity<Void> startOrdersDelivery(
            @RequestBody OrdersStatusRequestDTO ordersStatusRequestDTO) {
        ordersService.deliveryOrders(ordersStatusRequestDTO.getOrdersId());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "입고 완료")
    @PatchMapping("/check")
    public ResponseEntity<Void> checkOrdersArrived(
            @RequestBody OrdersStatusRequestDTO ordersStatusRequestDTO) {
        ordersService.checkOrders(ordersStatusRequestDTO.getOrdersId());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
