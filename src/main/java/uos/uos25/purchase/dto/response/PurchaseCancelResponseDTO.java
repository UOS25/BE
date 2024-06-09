package uos.uos25.purchase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseCancelResponseDTO {
    private final Integer canceledPrice;
}
