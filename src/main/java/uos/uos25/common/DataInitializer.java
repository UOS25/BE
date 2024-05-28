package uos.uos25.common;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uos.uos25.product.entity.Product;
import uos.uos25.product.repository.ProductRepository;
import uos.uos25.product.service.ProductService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final ProductRepository productRepository;

    @PostConstruct
    public void init(){
        Product product = Product.builder()
                .enterprise("enterprise")
                .productName("name")
                .barcode("barcode")
                .customerPrice(1000)
                .orderPrice(2000)
                .category("category")
                .description("description")
                .feature("feature")
                .expirationDate(LocalDateTime.now())
                .build();

        Product save = productRepository.save(product);

    }
}
