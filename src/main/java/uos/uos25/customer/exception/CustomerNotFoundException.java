package uos.uos25.customer.exception;

import lombok.Getter;
import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

@Getter
public class CustomerNotFoundException extends BusinessException {

    public CustomerNotFoundException() {
        super(ErrorCode.CUSTOMER_NOT_FOUND);
    }
}
