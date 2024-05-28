package uos.uos25.returns.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.uos25.returns.dto.request.ReturnsRequestDTO;
import uos.uos25.returns.service.ReturnsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returns")
public class ReturnsController {
    private final ReturnsService returnsService;

    @PostMapping
    public ResponseEntity<Long> createReturns(@RequestBody ReturnsRequestDTO returnsRequestDTO){
        return ResponseEntity.ok(returnsService.createReturns(returnsRequestDTO));
    }
}
