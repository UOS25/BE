package uos.uos25.event.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class EventNotFound extends BusinessException {
    public EventNotFound() {
        super(ErrorCode.EVENT_NOT_FOUND);
    }
}
