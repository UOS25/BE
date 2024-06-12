package uos.uos25.user.receipt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.user.receipt.dto.response.ReceiptGetResponseDTO;
import uos.uos25.user.receipt.entity.Receipt;
import uos.uos25.user.receipt.service.ReceiptService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receipt")
@Tag(name = "영수증")
public class ReceiptController {
    private final ReceiptService receiptService;

    @GetMapping("/{receiptId}")
    public ResponseEntity<ReceiptGetResponseDTO> getReceipt(
            @Parameter(example = "1") @PathVariable Long receiptId) {
        Receipt receipt = receiptService.findById(receiptId);

        return ResponseEntity.status(HttpStatus.OK).body(ReceiptGetResponseDTO.fromEntity(receipt));
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<List<ReceiptGetResponseDTO>> getReceiptByPhoneNumber(
            @Parameter(example = "01012341234") @PathVariable String phoneNumber) {
        List<Receipt> receipts = receiptService.findByCustomerPhoneNumber(phoneNumber);
        List<ReceiptGetResponseDTO> receiptGetResponseDTOS =
                receipts.stream()
                        .map(receipt -> ReceiptGetResponseDTO.fromEntity(receipt))
                        .toList();

        return ResponseEntity.status(HttpStatus.OK).body(receiptGetResponseDTOS);
    }
}
