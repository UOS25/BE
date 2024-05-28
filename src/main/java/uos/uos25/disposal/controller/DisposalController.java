package uos.uos25.disposal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.disposal.service.DisposalService;
import uos.uos25.returns.dto.request.ReturnsRequestDTO;

@RestController
@RequestMapping("/disposal")
@RequiredArgsConstructor
public class DisposalController {
    private final DisposalService disposalService;

    @PostMapping
    public ResponseEntity<Disposal> dispose(@RequestBody ReturnsRequestDTO returnsRequestDTO){
        Disposal disposal = disposalService.createDisposal(returnsRequestDTO);

        return ResponseEntity.ok(disposal);
    }
}
