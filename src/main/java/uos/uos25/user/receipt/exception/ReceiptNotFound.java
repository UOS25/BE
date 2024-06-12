package uos.uos25.user.receipt.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class ReceiptNotFound extends BusinessException {
    public ReceiptNotFound() {
        super(ErrorCode.RECEIPT_NOT_FOUND);
    }
}
