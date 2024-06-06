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
    private final LocalDateTime disburseDate;
    private final Long disburseAmount;
    private final ShopInfo shopInfo;

    @Builder
    public DisbursementGetResponseDTO(
            Long disburseId, LocalDateTime disburseDate, Long disburseAmount, ShopInfo shopInfo) {
        this.disburseId = disburseId;
        this.disburseDate = disburseDate;
        this.disburseAmount = disburseAmount;
        this.shopInfo = shopInfo;
    }

    public static DisbursementGetResponseDTO fromEntity(Disbursement disbursement) {
        ShopInfo shopInfo =
                new ShopInfo(
                        disbursement.getShop().getShopId(), disbursement.getShop().getShopName());

        return DisbursementGetResponseDTO.builder()
                .disburseId(disbursement.getDisburseId())
                .disburseDate(disbursement.getDisburseDate())
                .disburseAmount(disbursement.getDisburseAmount())
                .shopInfo(shopInfo)
                .build();
    }
}
