package uos.uos25.disposal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.disposal.dto.request.DisposalListRequestDTO;
import uos.uos25.disposal.dto.request.DisposalReqeustDTO;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.disposal.service.DisposalService;

import java.util.List;

@RestController
@RequestMapping("/disposal")
@RequiredArgsConstructor
public class DisposalController {
    private final DisposalService disposalService;

    @PostMapping
    public ResponseEntity<Disposal> dispose(@RequestBody DisposalReqeustDTO disposalReqeustDTO){
        Disposal disposal = disposalService.createDisposal(disposalReqeustDTO);

        return ResponseEntity.ok(disposal);
    }

    @GetMapping
    public ResponseEntity<List<Disposal>> getDisposalsBetweenStartAndEnd(@RequestBody DisposalListRequestDTO disposalListRequestDTO){
        List<Disposal> disposalsWithDate = disposalService.findDisposalsWithDate(disposalListRequestDTO);

        return ResponseEntity.ok(disposalsWithDate);
    }
}
