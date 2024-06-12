package uos.uos25.headquarter.dto.request;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductCreateRequestDTO {
    @Schema(example = "newBarcode")
    private final String barcode;

    @Schema(example = "newProdcut")
    private final String productName;

    @Schema(example = "newEnterprise")
    private final String enterprise;

    @Schema(example = "3000")
    private final Integer customerPrice;

    @Schema(example = "4000")
    private final Integer orderPrice;

    @Schema(example = "newCategory")
    private final String category;

    @Schema(example = "newDescription")
    private final String description;

    @Schema(example = "newFeature")
    private final String feature;

    @Schema(example = "2024-06-08T06:34:11.377Z")
    private final LocalDateTime expirationDate;

    @Builder
    public ProductCreateRequestDTO(
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
}
