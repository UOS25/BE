package uos.uos25.disposal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.disposal.dto.request.DisposalListRequestDTO;
import uos.uos25.disposal.dto.request.DisposalReqeustDTO;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.disposal.service.DisposalService;

@RestController
@RequestMapping("/disposal")
@RequiredArgsConstructor
@Tag(name = "폐기")
public class DisposalController {
    private final DisposalService disposalService;

    @PostMapping
    public ResponseEntity<DisposalCreateResponseDTO> dispose(
            @RequestBody DisposalCreateReqeustDTO disposalCreateReqeustDTO) {
        Disposal disposal = disposalService.createDisposal(disposalCreateReqeustDTO);
        DisposalCreateResponseDTO disposalCreateResponseDTO =
                DisposalCreateResponseDTO.fromEntity(disposal);

        return ResponseEntity.ok(disposalCreateResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<Disposal>> getDisposalsBetweenStartAndEnd(
            @RequestBody DisposalListRequestDTO disposalListRequestDTO) {
        List<Disposal> disposalsWithDate =
                disposalService.findDisposalsWithDate(disposalListRequestDTO);

        return ResponseEntity.ok(disposalsWithDate);
    }
}
