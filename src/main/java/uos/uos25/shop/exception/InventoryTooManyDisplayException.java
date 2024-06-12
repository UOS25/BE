package uos.uos25.shop.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class InventoryTooManyDisplayException extends BusinessException {
    public InventoryTooManyDisplayException() {
        super(ErrorCode.INVENTORY_TOO_MANY_DISPLAY);
    }
}
