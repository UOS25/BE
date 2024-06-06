package uos.uos25.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.product.dto.response.ProductGetResponseDTO;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "상품")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{barcode}")
    public ResponseEntity<ProductGetResponseDTO> getProductByBarcode(@PathVariable String barcode) {
        Product product = productService.findById(barcode);
        ProductGetResponseDTO productGetResponseDTO = ProductGetResponseDTO.fromProduct(product);

        return ResponseEntity.ok(productGetResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductGetResponseDTO>> getAllProductsList() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
