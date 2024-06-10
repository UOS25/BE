package uos.uos25.customer.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class CustomerAlreadyExists extends BusinessException {
    public CustomerAlreadyExists() {
        super(ErrorCode.CUSTOMER_ALREADY_EXISTS);
    }
}
