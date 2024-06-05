package uos.uos25.purchase.dto;

import lombok.Builder;
import lombok.Data;
import uos.uos25.receipt.entity.ReceiptDetail;

@Data
public class ItemInfo {
    private final String barcode;
    private final Integer ea;

    @Builder
    public ItemInfo(String barcode, Integer ea) {
        this.barcode = barcode;
        this.ea = ea;
    }

    public static ItemInfo fromReceiptDetail(ReceiptDetail receiptDetail) {
        return ItemInfo.builder()
                .barcode(receiptDetail.getProduct().getBarcode())
                .ea(receiptDetail.getEa())
                .build();
    }
}
