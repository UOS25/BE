package uos.uos25.returns.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class ReturnsNotFoundException extends BusinessException {

    public ReturnsNotFoundException() {
        super(ErrorCode.RETURNS_NOT_FOUND);
    }
}
