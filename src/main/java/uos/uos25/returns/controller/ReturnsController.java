package uos.uos25.returns.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.returns.dto.request.ReturnsCreateRequestDTO;
import uos.uos25.returns.dto.response.ReturnsCreateResponseDTO;
import uos.uos25.returns.dto.response.ReturnsGetResponseDTO;
import uos.uos25.returns.entity.Returns;
import uos.uos25.returns.service.ReturnsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returns")
@Tag(name = "반품", description = "본사로의")
public class ReturnsController {
    private final ReturnsService returnsService;

    @PostMapping
    public ResponseEntity<ReturnsCreateResponseDTO> createReturns(
            @RequestBody ReturnsCreateRequestDTO returnsCreateRequestDTO) {
        Returns returns = returnsService.createReturns(returnsCreateRequestDTO);
        ReturnsCreateResponseDTO returnsCreateResponseDTO =
                ReturnsCreateResponseDTO.fromEntity(returns);

        return ResponseEntity.ok(returnsCreateResponseDTO);
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<List<ReturnsGetResponseDTO>> getAllReturnsByShopId(
            @PathVariable Long shopId) {
        List<Returns> returnses = returnsService.findAllByShopId(shopId);
        List<ReturnsGetResponseDTO> returnsGetResponseDTOS =
                returnses.stream()
                        .map(returns -> ReturnsGetResponseDTO.fromEntity(returns))
                        .toList();

        return ResponseEntity.ok(returnsGetResponseDTOS);
    }
}
