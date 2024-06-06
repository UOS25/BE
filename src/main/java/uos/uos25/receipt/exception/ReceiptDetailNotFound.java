package uos.uos25.receipt.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class ReceiptDetailNotFound extends BusinessException {
    public ReceiptDetailNotFound() {
        super(ErrorCode.RECEIPT_DETAILS_NOT_FOUND);
    }
}
