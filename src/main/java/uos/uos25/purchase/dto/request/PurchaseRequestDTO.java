package uos.uos25.purchase.dto.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import uos.uos25.purchase.dto.ItemInfo;

@Data
public class PurchaseRequestDTO {
    private final Long employeeId;
    private final String phoneNumber;
    private final Integer age;
    private final String gender;
    private final Integer mileage;
    private final List<ItemInfo> itemInfos;

    @Builder
    public PurchaseRequestDTO(
            Long employeeId,
            String phoneNumber,
            Integer age,
            String gender,
            Integer mileage,
            List<ItemInfo> itemInfos) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.mileage = mileage;
        this.itemInfos = itemInfos;
    }
}
