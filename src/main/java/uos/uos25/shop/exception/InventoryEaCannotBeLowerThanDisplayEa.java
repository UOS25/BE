package uos.uos25.shop.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class InventoryEaCannotBeLowerThanDisplayEa extends BusinessException {
    public InventoryEaCannotBeLowerThanDisplayEa() {
        super(ErrorCode.INVENTORY_EA_CANNOT_BE_LOWER_THAN_DISPLAY_EA);
    }
}
