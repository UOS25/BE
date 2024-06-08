package uos.uos25.statistics.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class StatisticsGetResponseDTO {
    private final Integer totalPrice;
    private final List<StatisticsDisbursementResponseDTO> disbursements;
    private final List<StatisticsReturnstResponseDTO> returnses;
    private final List<StatisticsOrdersResponseDTO> orderses;
    private final List<StatisticsDisposalResponseDTO> disposals;
    private final List<StatisticsReceiptResponseDTO> receipts;

    @Builder
    public StatisticsGetResponseDTO(
            List<StatisticsDisbursementResponseDTO> disbursements,
            List<StatisticsReturnstResponseDTO> returnses,
            List<StatisticsOrdersResponseDTO> orderses,
            List<StatisticsDisposalResponseDTO> disposals,
            List<StatisticsReceiptResponseDTO> receipts,
            Integer totalPrice) {
        this.disbursements = disbursements;
        this.returnses = returnses;
        this.orderses = orderses;
        this.disposals = disposals;
        this.receipts = receipts;
        this.totalPrice = totalPrice;
    }
}
