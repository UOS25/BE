package uos.uos25.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseCancelResponseDTO {
    private final Integer canceledPrice;
}
