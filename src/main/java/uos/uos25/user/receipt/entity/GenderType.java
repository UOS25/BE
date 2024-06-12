package uos.uos25.user.receipt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderType {
    MALE("남성"),
    FEMALE("여성");

    private String gender;
}
