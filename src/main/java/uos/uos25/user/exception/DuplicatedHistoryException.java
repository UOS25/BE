package uos.uos25.user.exception;

import lombok.Getter;
import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

@Getter
public class DuplicatedHistoryException extends BusinessException {
    public DuplicatedHistoryException() {
        super(ErrorCode.DUPLICATED_HISTORY);
    }
}
