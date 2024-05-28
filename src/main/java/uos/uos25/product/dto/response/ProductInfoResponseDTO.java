package uos.uos25.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import uos.uos25.entity.Event;
import uos.uos25.product.entity.Product;

import java.util.List;

@Data
public class ProductInfoResponseDTO {
    private final Long productId;
    private final String productName;
    private final Integer price;
    private final List<Event> events;

    @Builder
    public ProductInfoResponseDTO(Long productId, String productName, Integer price, List<Event> events) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.events = events;
    }

    public static ProductInfoResponseDTO fromProduct(Product product){
        ProductInfoResponseDTO productInfoResponseDTO = ProductInfoResponseDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getCustomerPrice())
                .events(product.getEvents())
                .build();

        return productInfoResponseDTO;
    }
}
