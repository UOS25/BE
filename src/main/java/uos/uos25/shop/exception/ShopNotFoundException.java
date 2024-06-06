package uos.uos25.shop.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class ShopNotFoundException extends BusinessException {
    public ShopNotFoundException() {
        super(ErrorCode.SHOP_NOT_FOUND);
    }
}
