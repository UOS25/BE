package uos.uos25.user.exception;

import uos.uos25.common.error.BusinessException;
import uos.uos25.common.error.ErrorCode;

public class EmployeeNotFound extends BusinessException {
    public EmployeeNotFound() {
        super(ErrorCode.EMPLOYEE_NOT_FOUND);
    }
}
