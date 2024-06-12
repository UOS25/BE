package uos.uos25.user.receipt.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class ReceiptDetailNotFound extends BusinessException {
    public ReceiptDetailNotFound() {
        super(ErrorCode.RECEIPT_DETAILS_NOT_FOUND);
    }
}
