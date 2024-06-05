package uos.uos25.purchase.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import uos.uos25.purchase.dto.ItemInfo;

@Data
public class PurchaseRequestDTO {
    @Schema(example = "1")
    private final Long employeeId;

    @Schema(example = "01012341234")
    private final String phoneNumber;

    @Schema(example = "26")
    private Integer age = null;

    @Schema(example = "FEMALE")
    private String gender = null;

    @Schema(example = "0")
    private Integer mileage = 0;

    @Schema(
            example =
                    """
            [
                {
                    "barcode" : "barcode",
                    "ea" : 5
                }
            ]
            """)
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
