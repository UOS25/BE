package uos.uos25.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<ProductInfoResponseDTO> getProductInfo(@RequestBody ProductInfoRequestDTO productInfoRequestDTO){
        Product productByBarcode = productService.findProductByBarcode(productInfoRequestDTO.getBarcode());
        ProductInfoResponseDTO productInfoResponseDTO = ProductInfoResponseDTO.fromProduct(productByBarcode);

        return ResponseEntity.ok(productInfoResponseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProductsList(){
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
