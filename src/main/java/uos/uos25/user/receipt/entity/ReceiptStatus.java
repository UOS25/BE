package uos.uos25.user.receipt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReceiptStatus {
    COMPLETED("구매완료"),
    CANCELED("환불");

    private String status;
}
