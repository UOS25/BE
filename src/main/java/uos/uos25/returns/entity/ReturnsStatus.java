package uos.uos25.returns.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReturnsStatus {
    REFUNDED("반품요청");

    private String status;
}
