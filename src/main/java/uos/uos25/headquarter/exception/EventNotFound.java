package uos.uos25.headquarter.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class EventNotFound extends BusinessException {
    public EventNotFound() {
        super(ErrorCode.EVENT_NOT_FOUND);
    }
}
