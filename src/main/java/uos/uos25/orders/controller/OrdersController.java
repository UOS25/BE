package uos.uos25.orders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.orders.dto.request.OrdersModifyRequestDTO;
import uos.uos25.orders.dto.request.OrdersRequestDTO;
import uos.uos25.orders.dto.request.OrdersStatusRequestDTO;
import uos.uos25.orders.entity.Orders;
import uos.uos25.orders.service.OrdersService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "주문", description = "본사로의")
public class OrdersController {
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Orders> order(@RequestBody OrdersRequestDTO ordersRequestDTO) {
        Orders savedOrders = ordersService.save(ordersRequestDTO);
        return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Orders>> getList() {
        return new ResponseEntity<>(ordersService.findOrders(), HttpStatus.OK);
    }

    @PatchMapping("/modify")
    public ResponseEntity<Long> modifyOrders(
            @RequestBody OrdersModifyRequestDTO ordersModifyRequestDTO) {
        Long modifiedOrdersId = ordersService.updateOrders(ordersModifyRequestDTO);
        return ResponseEntity.ok(modifiedOrdersId);
    }

    @DeleteMapping("/{ordersId}")
    public ResponseEntity<Void> deleteOrders(@PathVariable Long ordersId) {
        ordersService.deleteOrdersById(ordersId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/delivery")
    public ResponseEntity<Long> startOrdersDelivery(
            @RequestBody OrdersStatusRequestDTO ordersStatusRequestDTO) {
        Long modifiedOrdersId = ordersService.modifyOrdersStatus(ordersStatusRequestDTO);
        return ResponseEntity.ok(modifiedOrdersId);
    }

    @PatchMapping("/check")
    public ResponseEntity<Long> checkOrdersArrived(
            @RequestBody OrdersStatusRequestDTO ordersStatusRequestDTO) {
        Long modifiedOrdersId = ordersService.modifyOrdersStatus(ordersStatusRequestDTO);
        return ResponseEntity.ok(modifiedOrdersId);
    }
}
