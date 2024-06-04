package uos.uos25.receipt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.uos25.receipt.dto.response.ReceiptGetResponseDTO;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.service.ReceiptService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;

    @GetMapping("/{receiptId}")
    public ResponseEntity<ReceiptGetResponseDTO> getReceipt(@PathVariable Long receiptId){
        Receipt receipt = receiptService.findById(receiptId);

        return ResponseEntity.status(HttpStatus.OK).body(receiptService.entityToDTO(receipt));
    }
}
