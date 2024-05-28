package uos.uos25.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.product.entity.Product;
import uos.uos25.product.exception.ProductNotFoundException;
import uos.uos25.product.repository.ProductRepository;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.exception.ShopNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product findProductById(Long productId){
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
    }
}
