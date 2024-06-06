package uos.uos25.purchase.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.purchase.dto.ItemInfo;
import uos.uos25.purchase.dto.request.PurchaseCreateRequestDTO;
import uos.uos25.purchase.sevice.PurchaseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
@Tag(name = "구매", description = "고객이 행하는")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Void> purchase(
            @RequestBody PurchaseCreateRequestDTO purchaseCreateRequestDTO) {
        Long employeeId = purchaseCreateRequestDTO.getEmployeeId();
        String phoneNumber = purchaseCreateRequestDTO.getPhoneNumber();
        Integer age = purchaseCreateRequestDTO.getAge();
        String gender = purchaseCreateRequestDTO.getGender();
        Integer mileage = purchaseCreateRequestDTO.getMileage();
        List<ItemInfo> itemInfos = purchaseCreateRequestDTO.getItemInfos();

        purchaseService.purchase(employeeId, phoneNumber, age, gender, mileage, itemInfos);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{receiptId}")
    public ResponseEntity<Void> cancelPurchase(@PathVariable("receiptId") Long receiptId) {
        purchaseService.cancel(receiptId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
