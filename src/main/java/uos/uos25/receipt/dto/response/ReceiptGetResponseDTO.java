package uos.uos25.receipt.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import uos.uos25.purchase.dto.ItemInfo;

@Data
public class ReceiptGetResponseDTO {
    private final Long receiptId;
    private final String shopName;
    private final List<ItemInfo> itemInfos;
    private final LocalDateTime purchaseDate;

    @Builder
    public ReceiptGetResponseDTO(
            Long receiptId, String shopName, List<ItemInfo> itemInfos, LocalDateTime purchaseDate) {
        this.receiptId = receiptId;
        this.shopName = shopName;
        this.itemInfos = itemInfos;
        this.purchaseDate = purchaseDate;
    }
}
