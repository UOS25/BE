package uos.uos25.headquarter.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
