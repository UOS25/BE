package uos.uos25.returns.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.returns.dto.request.ReturnsRequestDTO;
import uos.uos25.returns.dto.response.ReturnsResponseDTO;
import uos.uos25.returns.service.ReturnsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returns")
public class ReturnsController {
    private final ReturnsService returnsService;

    @PostMapping
    public ResponseEntity<Long> createReturns(@RequestBody ReturnsRequestDTO returnsRequestDTO){
        return ResponseEntity.ok(returnsService.createReturns(returnsRequestDTO));
    }

    @GetMapping("/list/{shopId}")
    public ResponseEntity<List<ReturnsResponseDTO>> getAllReturnsByShopId(@PathVariable Long shopId){
        return ResponseEntity.ok(returnsService.findAllByShopId(shopId));
    }
}
