package uos.uos25.product.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import uos.uos25.product.entity.Product;

@Data
public class ProductCreateResponseDTO {
    private final String barcode;
    private final String productName;
    private final String enterprise;
    private final Integer customerPrice;
    private final Integer orderPrice;
    private final String category;
    private final String description;
    private final String feature;
    private final LocalDateTime expirationDate;

    @Builder
    public ProductCreateResponseDTO(
            String barcode,
            String productName,
            String enterprise,
            Integer customerPrice,
            Integer orderPrice,
            String category,
            String description,
            String feature,
            LocalDateTime expirationDate) {
        this.barcode = barcode;
        this.productName = productName;
        this.enterprise = enterprise;
        this.customerPrice = customerPrice;
        this.orderPrice = orderPrice;
        this.category = category;
        this.description = description;
        this.feature = feature;
        this.expirationDate = expirationDate;
    }

    public static ProductCreateResponseDTO fromEntity(Product product) {
        return ProductCreateResponseDTO.builder()
                .barcode(product.getBarcode())
                .productName(product.getProductName())
                .enterprise(product.getEnterprise())
                .customerPrice(product.getCustomerPrice())
                .orderPrice(product.getOrderPrice())
                .category(product.getCategory())
                .description(product.getDescription())
                .feature(product.getFeature())
                .expirationDate(product.getExpirationDate())
                .build();
    }
}
