package uos.uos25.returns.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.returns.dto.request.ReturnsRequestDTO;
import uos.uos25.returns.dto.response.ReturnsResponseDTO;
import uos.uos25.returns.entity.Returns;
import uos.uos25.returns.service.ReturnsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returns")
@Tag(name = "반품", description = "본사로의")
public class ReturnsController {
    private final ReturnsService returnsService;

    @PostMapping
    public ResponseEntity<Long> createReturns(@RequestBody ReturnsRequestDTO returnsRequestDTO) {
        return ResponseEntity.ok(returnsService.createReturns(returnsRequestDTO));
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<List<ReturnsResponseDTO>> getAllReturnsByShopId(
            @PathVariable Long shopId) {
        List<Returns> returnses = returnsService.findAllByShopId(shopId);
        List<ReturnsResponseDTO> returnsResponseDTOS =
                returnses.stream().map(returns -> ReturnsResponseDTO.fromEntity(returns)).toList();

        return ResponseEntity.ok(returnsResponseDTOS);
    }
}
