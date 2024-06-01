package uos.uos25.customer.exception;

import lombok.Getter;

@Getter
public class CustomerNotFoundException extends RuntimeException{

    private final String msg;

    public CustomerNotFoundException(String msg) {
        this.msg = msg;
    }
}
