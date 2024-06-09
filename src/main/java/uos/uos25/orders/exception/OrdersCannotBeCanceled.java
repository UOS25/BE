package uos.uos25.orders.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class OrdersCannotBeCanceled extends BusinessException {
    public OrdersCannotBeCanceled() {
        super(ErrorCode.ORDERS_CANNOT_BE_CANCELED);
    }
}
