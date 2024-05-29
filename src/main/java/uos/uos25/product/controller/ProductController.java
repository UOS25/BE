package uos.uos25.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.product.dto.request.ProductInfoRequestDTO;
import uos.uos25.product.dto.response.ProductInfoResponseDTO;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductInfoResponseDTO> getProductInfo(@RequestParam String barcode){
        Product productByBarcode = productService.findProductByBarcode(barcode);
        ProductInfoResponseDTO productInfoResponseDTO = ProductInfoResponseDTO.fromProduct(productByBarcode);

        return ResponseEntity.ok(productInfoResponseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProductsList(){
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
