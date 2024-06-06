package uos.uos25.employee.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "uos.uos25.employee")
public class EmployeeExceptionHandler {

    // DuplicatedHistoryException 예외 처리
    // 출근 메시지를 띄운 후 HttpStatus.OK를 반환합니다.
    @ExceptionHandler(DuplicatedHistoryException.class)
    public ResponseEntity<String> handleDuplicatedHistoryException(DuplicatedHistoryException ex) {
        System.out.println("DuplicatedHistoryException 처리됨: " + ex.getMsg());
        return new ResponseEntity<>(ex.getMsg(), HttpStatus.OK);
    }

    // MethodArgumentNotValidException 예외 처리
    // @Valid에서 발생한 익셉션 캐치
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            String fieldName = ((FieldError) error).getField();
                            String errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // IllegalArgumentException 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
