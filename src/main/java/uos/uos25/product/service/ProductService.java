package uos.uos25.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.product.dto.response.ProductInfoResponseDTO;
import uos.uos25.product.entity.Product;
import uos.uos25.product.exception.ProductNotFoundException;
import uos.uos25.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findProductById(Long productId){
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
    }

    public List<ProductInfoResponseDTO> findAllProducts(){
        return productRepository.findAll().stream()
                .map(product -> ProductInfoResponseDTO.fromProduct(product))
                .toList();
    }

    public Product findProductByBarcode(String barcode){
        return productRepository.findByBarcode(barcode).orElseThrow(() -> new ProductNotFoundException());
    }
}
