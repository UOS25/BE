package uos.uos25.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DisbursementType {
    SALARY("월급"),
    ROYALTY("로열티");

    private String type;
}
