package uos.uos25.receipt.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class ReceiptCannotBeCanceledException extends BusinessException {
    public ReceiptCannotBeCanceledException() {
        super(ErrorCode.RECEIPT_CANNOT_BE_CANCELED);
    }
}
