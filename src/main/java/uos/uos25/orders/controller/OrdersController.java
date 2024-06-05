package uos.uos25.orders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.orders.dto.request.OrdersCreateRequestDTO;
import uos.uos25.orders.dto.request.OrdersUpdateRequestDTO;
import uos.uos25.orders.dto.request.OrdersStatusRequestDTO;
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
    public ResponseEntity<Orders> order(
            @RequestBody OrdersCreateRequestDTO ordersCreateRequestDTO) {
        Orders savedOrders = ordersService.save(ordersCreateRequestDTO);
        return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrdersGetResponseDTO>> getList() {
        List<Orders> orders = ordersService.findAllOrders();
        List<OrdersGetResponseDTO> ordersGetResponseDTOS =
                orders.stream().map(order -> OrdersGetResponseDTO.fromEntity(order)).toList();

        return ResponseEntity.ok(ordersGetResponseDTOS);
    }

    @GetMapping("/{ordersId}")
    public ResponseEntity<OrdersGetResponseDTO> getOrdersById(@PathVariable Long ordersId) {
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

    @PatchMapping("/delivery")
    public ResponseEntity<Void> startOrdersDelivery(
            @RequestBody OrdersStatusRequestDTO ordersStatusRequestDTO) {
        Long modifiedOrdersId = ordersService.modifyOrdersStatus(ordersStatusRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/check")
    public ResponseEntity<Void> checkOrdersArrived(
            @RequestBody OrdersStatusRequestDTO ordersStatusRequestDTO) {
        Long modifiedOrdersId = ordersService.modifyOrdersStatus(ordersStatusRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT);
    }
}
