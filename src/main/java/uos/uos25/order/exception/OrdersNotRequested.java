package uos.uos25.order.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class OrdersNotRequested extends BusinessException {
    public OrdersNotRequested() {
        super(ErrorCode.ORDERS_NOT_REQUESTED);
    }
}
