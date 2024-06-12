package uos.uos25.shop.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class InventoryNotFoundException extends BusinessException {
    public InventoryNotFoundException() {
        super(ErrorCode.INVENTORY_NOT_FOUND);
    }
}
