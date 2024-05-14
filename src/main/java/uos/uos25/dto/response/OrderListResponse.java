package uos.uos25.dto.response;

import lombok.Data;
import uos.uos25.orders.entity.Orders;

import java.util.List;

@Data
public class OrderListResponse {
    private List<Orders> orders;
}
