package uos.uos25.receipt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReceiptStatus {
    COMPLETED("구매"),
    CANCELED("환불");

    private String status;
}
