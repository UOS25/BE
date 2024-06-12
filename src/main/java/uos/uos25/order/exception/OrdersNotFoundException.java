package uos.uos25.order.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class OrdersNotFoundException extends BusinessException {
    public OrdersNotFoundException() {
        super(ErrorCode.ORDERS_NOT_FOUND);
    }
}
