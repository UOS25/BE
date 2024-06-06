package uos.uos25.employee.exception;

import lombok.Getter;

@Getter
public class DuplicatedHistoryException extends RuntimeException {
    private final String msg;

    public DuplicatedHistoryException(String msg) {
        this.msg = msg;
    }
}
