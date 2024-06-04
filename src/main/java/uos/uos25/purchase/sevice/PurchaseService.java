package uos.uos25.purchase.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.purchase.dto.ItemInfo;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.service.ReceiptDetailService;
import uos.uos25.receipt.service.ReceiptService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final ProductService productService;
    private final ReceiptDetailService receiptDetailService;
    private final ReceiptService receiptService;

    public void purchase(Long shopId, Long employeeId, String customerId, Integer age, String gender, List<ItemInfo> itemInfos) {
        Receipt receipt = receiptService.create(employeeId, customerId, age, gender);

        for (ItemInfo itemInfo : itemInfos) {
            Product product = productService.findProductByBarcode(itemInfo.getBarcode());

            receiptDetailService.create(receipt, product.getProductId(), itemInfo.getEa());
        }
    }
}
