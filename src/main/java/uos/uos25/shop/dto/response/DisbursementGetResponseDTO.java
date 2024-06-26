package uos.uos25.shop.dto.response;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Data;
import uos.uos25.shop.dto.ShopInfo;
import uos.uos25.shop.entity.Disbursement;

@Data
public class DisbursementGetResponseDTO {
    private final Long disburseId;
    private final Integer disburseAmount;
    private String disburseType;
    private final ShopInfo shopInfo;
    private final LocalDateTime disburseDate;

    @Builder
    public DisbursementGetResponseDTO(
            Long disburseId,
            Integer disburseAmount,
            String disburseType,
            ShopInfo shopInfo,
            LocalDateTime disburseDate) {
        this.disburseId = disburseId;
        this.disburseAmount = disburseAmount;
        this.disburseType = disburseType;
        this.shopInfo = shopInfo;
        this.disburseDate = disburseDate;
    }

    public static DisbursementGetResponseDTO fromEntity(Disbursement disbursement) {
        ShopInfo shopInfo =
                new ShopInfo(
                        disbursement.getShop().getShopId(), disbursement.getShop().getShopName());

        return DisbursementGetResponseDTO.builder()
                .disburseId(disbursement.getDisburseId())
                .disburseAmount(disbursement.getDisburseAmount())
                .disburseType(disbursement.getDisburseType())
                .shopInfo(shopInfo)
                .disburseDate(disbursement.getCreatedAt())
                .build();
    }
}
