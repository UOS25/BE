package uos.uos25.receipt.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import uos.uos25.purchase.dto.ItemInfo;
import uos.uos25.receipt.entity.Receipt;

@Data
public class ReceiptGetResponseDTO {
    private final Long receiptId;
    private final String shopName;
    private final String purchaseStatus;
    private final List<ItemInfo> itemInfos;
    private final LocalDateTime purchaseDate;

    @Builder
    public ReceiptGetResponseDTO(
            Long receiptId,
            String shopName,
            String purchaseStatus,
            List<ItemInfo> itemInfos,
            LocalDateTime purchaseDate) {
        this.receiptId = receiptId;
        this.shopName = shopName;
        this.purchaseStatus = purchaseStatus;
        this.itemInfos = itemInfos;
        this.purchaseDate = purchaseDate;
    }

    public static ReceiptGetResponseDTO fromEntity(Receipt receipt) {
        List<ItemInfo> itemInfos =
                receipt.getReceiptDetails().stream()
                        .map(receiptDetail -> ItemInfo.fromReceiptDetail(receiptDetail))
                        .toList();

        return ReceiptGetResponseDTO.builder()
                .receiptId(receipt.getReceiptId())
                .shopName(receipt.getEmployee().getShop().getShopName())
                .purchaseStatus(receipt.getPurchaseStatus())
                .itemInfos(itemInfos)
                .purchaseDate(receipt.getPurchaseDate())
                .build();
    }
}
