package uos.uos25.order.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class TooManyMileageException extends BusinessException {
    public TooManyMileageException() {
        super(ErrorCode.TOO_MANY_MILEAGE);
    }
}
