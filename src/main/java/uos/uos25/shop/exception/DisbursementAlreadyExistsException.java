package uos.uos25.shop.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class DisbursementAlreadyExistsException extends BusinessException {
    public DisbursementAlreadyExistsException() {
        super(ErrorCode.DISBURSEMENT_ALREADY_EXISTS);
    }
}
