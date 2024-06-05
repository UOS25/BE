package uos.uos25.purchase.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.purchase.dto.ItemInfo;
import uos.uos25.purchase.dto.request.PurchaseRequestDTO;
import uos.uos25.purchase.sevice.PurchaseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
@Tag(name = "구매", description = "고객이 행하는")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public void purchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO) {
        Long employeeId = purchaseRequestDTO.getEmployeeId();
        String phoneNumber = purchaseRequestDTO.getPhoneNumber();
        Integer age = purchaseRequestDTO.getAge();
        String gender = purchaseRequestDTO.getGender();
        Integer mileage = purchaseRequestDTO.getMileage();
        List<ItemInfo> itemInfos = purchaseRequestDTO.getItemInfos();

        purchaseService.purchase(employeeId, phoneNumber, age, gender, mileage, itemInfos);
    }

    @DeleteMapping("/{receiptId}")
    public void cancelPurchase(@PathVariable("receiptId") Long receiptId) {
        purchaseService.cancel(receiptId);
    }
}
