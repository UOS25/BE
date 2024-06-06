package uos.uos25.receipt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.receipt.dto.response.ReceiptGetResponseDTO;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.service.ReceiptService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receipt")
@Tag(name = "영수증")
public class ReceiptController {
    private final ReceiptService receiptService;

    @GetMapping("/{receiptId}")
    public ResponseEntity<ReceiptGetResponseDTO> getReceipt(@PathVariable Long receiptId) {
        Receipt receipt = receiptService.findById(receiptId);

        return ResponseEntity.status(HttpStatus.OK).body(ReceiptGetResponseDTO.fromEntity(receipt));
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<List<ReceiptGetResponseDTO>> getReceiptByPhoneNumber(
            @PathVariable String phoneNumber) {
        List<Receipt> receipts = receiptService.findByCustomerPhoneNumber(phoneNumber);
        List<ReceiptGetResponseDTO> receiptGetResponseDTOS =
                receipts.stream()
                        .map(receipt -> ReceiptGetResponseDTO.fromEntity(receipt))
                        .toList();

        return ResponseEntity.status(HttpStatus.OK).body(receiptGetResponseDTOS);
    }
}
