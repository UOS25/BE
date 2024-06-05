package uos.uos25.product.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import uos.uos25.event.entity.Event;
import uos.uos25.product.entity.Product;

@Data
public class ProductInfoResponseDTO {
    private final String barcode;
    private final String productName;
    private final String enterprise;
    private final Integer customerPrice;
    private final Integer orderPrice;
    private final String category;
    private final String feature;
    private final String description;
    private final LocalDateTime expirationDate;
    private final List<String> eventNames;

    @Builder
    public ProductInfoResponseDTO(
            String barcode,
            String productName,
            String enterprise,
            String description,
            Integer customerPrice,
            Integer orderPrice,
            String category,
            String feature,
            LocalDateTime expirationDate,
            List<String> eventNames) {
        this.barcode = barcode;
        this.productName = productName;
        this.enterprise = enterprise;
        this.description = description;
        this.customerPrice = customerPrice;
        this.orderPrice = orderPrice;
        this.category = category;
        this.feature = feature;
        this.expirationDate = expirationDate;
        this.eventNames = eventNames;
    }

    public static ProductInfoResponseDTO fromProduct(Product product) {
        ProductInfoResponseDTO productInfoResponseDTO =
                ProductInfoResponseDTO.builder()
                        .barcode(product.getBarcode())
                        .productName(product.getProductName())
                        .enterprise(product.getEnterprise())
                        .description(product.getDescription())
                        .customerPrice(product.getCustomerPrice())
                        .orderPrice(product.getOrderPrice())
                        .category(product.getCategory())
                        .feature(product.getFeature())
                        .expirationDate(product.getExpirationDate())
                        .barcode(product.getBarcode())
                        .eventNames(product.getEvents().stream().map(Event::getEventName).toList())
                        .build();

        return productInfoResponseDTO;
    }
}
