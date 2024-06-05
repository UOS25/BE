package uos.uos25.purchase.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.uos25.purchase.dto.ItemInfo;
import uos.uos25.purchase.dto.request.PurchaseRequestDTO;
import uos.uos25.purchase.sevice.PurchaseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
@Tag(name = "구매", description = "고객이 행하는")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public void purchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO){
        Long shopId = purchaseRequestDTO.getShopId();
        Long employeeId = purchaseRequestDTO.getEmployeeId();
        String phoneNumber = purchaseRequestDTO.getPhoneNumber();
        Integer age = purchaseRequestDTO.getAge();
        String gender = purchaseRequestDTO.getGender();
        List<ItemInfo> itemInfos = purchaseRequestDTO.getItemInfos();

        purchaseService.purchase(shopId, employeeId, phoneNumber, age, gender, itemInfos);
    }

}
