package uos.uos25.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrdersStatus {
    REQUEST("주문요청"),
    DELIVERED("배송중"),
    STORED("입고"),
    REFUNDED("반품");

    private String status;
}
