package uos.uos25.statistics.dto.response;

import lombok.Data;
import uos.uos25.shop.dto.response.DisbursementGetResponseDTO;

@Data
public class StatisticsDisbursementResponseDTO {
    private final DisbursementGetResponseDTO disbursement;
    private final Integer price;

    public StatisticsDisbursementResponseDTO(
            DisbursementGetResponseDTO disbursement, Integer price) {
        this.disbursement = disbursement;
        this.price = price;
    }
}
