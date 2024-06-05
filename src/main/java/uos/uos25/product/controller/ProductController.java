package uos.uos25.product.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.product.dto.response.ProductInfoResponseDTO;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "상품")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{barcode}")
    public ResponseEntity<ProductInfoResponseDTO> getProductByBarcode(@PathVariable String barcode){
        Product product = productService.findById(barcode);
        ProductInfoResponseDTO productInfoResponseDTO = ProductInfoResponseDTO.fromProduct(product);

        return ResponseEntity.ok(productInfoResponseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductInfoResponseDTO>> getAllProductsList(){
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
