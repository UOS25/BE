package uos.uos25.inventory.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class TooManyProductPurchaseException extends BusinessException {
    public TooManyProductPurchaseException() {
        super(ErrorCode.TOO_MANY_PRODUCT_PURCHASE);
    }
}
