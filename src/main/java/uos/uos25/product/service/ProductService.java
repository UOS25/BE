package uos.uos25.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.uos25.product.dto.request.ProductCreateRequestDTO;
import uos.uos25.product.dto.response.ProductGetResponseDTO;
import uos.uos25.product.entity.Product;
import uos.uos25.product.exception.ProductNotFoundException;
import uos.uos25.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product create(ProductCreateRequestDTO productCreateRequestDTO) {
        Product product =
                Product.builder()
                        .barcode(productCreateRequestDTO.getBarcode())
                        .productName(productCreateRequestDTO.getProductName())
                        .enterprise(productCreateRequestDTO.getEnterprise())
                        .customerPrice(productCreateRequestDTO.getCustomerPrice())
                        .orderPrice(productCreateRequestDTO.getOrderPrice())
                        .category(productCreateRequestDTO.getCategory())
                        .description(productCreateRequestDTO.getDescription())
                        .feature(productCreateRequestDTO.getFeature())
                        .expirationDate(productCreateRequestDTO.getExpirationDate())
                        .build();

        return productRepository.save(product);
    }

    public Product findById(String barcode) {
        return productRepository
                .findById(barcode)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public List<ProductGetResponseDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductGetResponseDTO.fromProduct(product))
                .toList();
    }
}
