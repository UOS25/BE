package uos.uos25.purchase.dto;

import lombok.Builder;
import lombok.Data;
import uos.uos25.receipt.entity.ReceiptDetail;

@Data
public class ItemInfo {
    private final String barcode;
    private final String productName;
    private final Integer ea;
    private final Integer price;

    @Builder
    public ItemInfo(String barcode, String productName, Integer ea, Integer price) {
        this.barcode = barcode;
        this.productName = productName;
        this.ea = ea;
        this.price = price;
    }

    public static ItemInfo fromReceiptDetail(ReceiptDetail receiptDetail) {
        return ItemInfo.builder()
                .barcode(receiptDetail.getProduct().getBarcode())
                .productName(receiptDetail.getProduct().getProductName())
                .ea(receiptDetail.getEa())
                .price(receiptDetail.getProduct().getCustomerPrice())
                .build();
    }
}
