package uos.uos25.receipt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.uos25.receipt.service.ReceiptService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;
}
