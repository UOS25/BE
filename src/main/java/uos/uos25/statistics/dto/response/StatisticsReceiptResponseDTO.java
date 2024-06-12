package uos.uos25.statistics.dto.response;

import lombok.Data;
import uos.uos25.user.receipt.dto.response.ReceiptGetResponseDTO;

@Data
public class StatisticsReceiptResponseDTO {
    private final ReceiptGetResponseDTO receipt;
    private final Integer price;
}
