package uos.uos25.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.product.dto.response.ProductInfoResponseDTO;
import uos.uos25.product.entity.Product;
import uos.uos25.product.exception.ProductNotFoundException;
import uos.uos25.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(String barcode) {
        return productRepository
                .findById(barcode)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public List<ProductInfoResponseDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductInfoResponseDTO.fromProduct(product))
                .toList();
    }
}
