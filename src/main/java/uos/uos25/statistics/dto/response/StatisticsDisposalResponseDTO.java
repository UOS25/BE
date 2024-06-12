package uos.uos25.statistics.dto.response;

import lombok.Data;
import uos.uos25.order.dto.response.DisposalGetResponseDTO;

@Data
public class StatisticsDisposalResponseDTO {
    private final DisposalGetResponseDTO disposal;
    private final Integer price;
}
