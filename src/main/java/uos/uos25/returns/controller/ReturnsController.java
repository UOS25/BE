package uos.uos25.returns.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.returns.dto.response.ReturnsGetResponseDTO;
import uos.uos25.returns.entity.Returns;
import uos.uos25.returns.service.ReturnsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returns")
@Tag(name = "반품", description = "본사로의")
public class ReturnsController {
    private final ReturnsService returnsService;

    @Operation(summary = "주문 취소")
    @PostMapping("/{ordersId}/cancel")
    public ResponseEntity<Void> createReturns(
            @Parameter(example = "1") @PathVariable Long ordersId) {
        returnsService.returnOrders(ordersId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{shopId}")
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
}
