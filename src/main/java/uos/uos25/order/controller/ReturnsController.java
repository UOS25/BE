package uos.uos25.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.order.dto.request.ReturnsCreateRequestDTO;
import uos.uos25.order.dto.response.ReturnsGetResponseDTO;
import uos.uos25.order.entity.Returns;
import uos.uos25.order.service.ReturnsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returns")
@Tag(name = "반품", description = "본사로의")
public class ReturnsController {
    private final ReturnsService returnsService;

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<ReturnsGetResponseDTO>> getAllReturnsByShopId(
            @Parameter(example = "1") @PathVariable Long shopId) {
        List<Returns> returnses = returnsService.findAllByShopId(shopId);
        List<ReturnsGetResponseDTO> returnsGetResponseDTOS =
                returnses.stream()
                        .map(returns -> ReturnsGetResponseDTO.fromEntity(returns))
                        .toList();

        return ResponseEntity.ok(returnsGetResponseDTOS);
    }

    @GetMapping("/{returnsId}")
    public ResponseEntity<ReturnsGetResponseDTO> getById(
            @Parameter(example = "1") @PathVariable Long returnsId) {
        Returns returns = returnsService.findById(returnsId);
        ReturnsGetResponseDTO returnsGetResponseDTO = ReturnsGetResponseDTO.fromEntity(returns);

        return ResponseEntity.ok(returnsGetResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> returnProduct(
            @RequestBody ReturnsCreateRequestDTO returnsCreateRequestDTO) {
        returnsService.returnProduct(returnsCreateRequestDTO);

        return ResponseEntity.noContent().build();
    }
}
