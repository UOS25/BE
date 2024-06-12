package uos.uos25.statistics.dto.response;

import lombok.Data;
import uos.uos25.order.dto.response.ReturnsGetResponseDTO;

@Data
public class StatisticsReturnstResponseDTO {
    private final ReturnsGetResponseDTO returns;
    private final Integer price;
}
