package uos.uos25.purchase.dto.request;

import lombok.Builder;
import lombok.Data;
import uos.uos25.purchase.dto.ItemInfo;

import java.util.List;

@Data
public class PurchaseRequestDTO {
    private final Long shopId;
    private final Long employeeId;
    private final String customerId;
    private final Integer age;
    private final String gender;
    private final List<ItemInfo> itemInfos;

    @Builder
    public PurchaseRequestDTO(Long shopId, Long employeeId, String customerId, Integer age, String gender, List<ItemInfo> itemInfos) {
        this.shopId = shopId;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.age = age;
        this.gender = gender;
        this.itemInfos = itemInfos;
    }
}
