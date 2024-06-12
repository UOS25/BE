package uos.uos25.shop.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class InventoryEaNotEnoughException extends BusinessException {
    public InventoryEaNotEnoughException() {
        super(ErrorCode.INVENTORY_EA_NOT_ENOUGH);
    }
}
