package uos.uos25.orders.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class OrdersNotDelivering extends BusinessException {
    public OrdersNotDelivering() {
        super(ErrorCode.ORDERS_NOT_DELIVERING);
    }
}
