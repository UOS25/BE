package uos.uos25.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.product.dto.request.ProductCreateRequestDTO;
import uos.uos25.product.dto.response.ProductCreateResponseDTO;
import uos.uos25.product.dto.response.ProductGetResponseDTO;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "상품")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductCreateResponseDTO> createProduct(
            @RequestBody ProductCreateRequestDTO productCreateRequestDTO) {
        Product product = productService.create(productCreateRequestDTO);
        ProductCreateResponseDTO productCreateResponseDTO =
                ProductCreateResponseDTO.fromEntity(product);

        return ResponseEntity.ok(productCreateResponseDTO);
    }

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

    @Operation(summary = "생활 서비스 조회")
    @GetMapping("/utility-service")
    public ResponseEntity<List<ProductGetResponseDTO>> getUtilityServices() {
        List<Product> utilityServices = productService.findUtilityServices();
        List<ProductGetResponseDTO> productGetResponseDTOS =
                utilityServices.stream()
                        .map(product -> ProductGetResponseDTO.fromProduct(product))
                        .toList();

        return ResponseEntity.ok(productGetResponseDTOS);
    }
}
