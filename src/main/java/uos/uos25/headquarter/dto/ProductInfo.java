package uos.uos25.headquarter.dto;

import lombok.Data;

@Data
public class ProductInfo {
    private final String barcode;
    private final String productName;

    public ProductInfo(String barcode, String productName) {
        this.barcode = barcode;
        this.productName = productName;
    }
}
