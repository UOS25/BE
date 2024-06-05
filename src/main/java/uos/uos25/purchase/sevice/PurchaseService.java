package uos.uos25.purchase.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.service.CustomerService;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.purchase.dto.ItemInfo;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.service.ReceiptDetailService;
import uos.uos25.receipt.service.ReceiptService;
import uos.uos25.util.MileageUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final ProductService productService;
    private final ReceiptDetailService receiptDetailService;
    private final ReceiptService receiptService;
    private final CustomerService customerService;

    @Transactional
    public void purchase(Long shopId, Long employeeId, String phoneNumber, Integer age, String gender, List<ItemInfo> itemInfos) {
        Receipt receipt = receiptService.create(employeeId, phoneNumber, age, gender);
        Customer customer = customerService.findById(phoneNumber);

        Integer totalPrice = 0;
        for (ItemInfo itemInfo : itemInfos) {
            Product product = productService.findById(itemInfo.getBarcode());
            Integer ea = itemInfo.getEa();
            totalPrice+= product.getCustomerPrice() * ea;

            receiptDetailService.create(receipt, product.getBarcode(), itemInfo.getEa());
        }
        customer.earnMileage(totalPrice);
    }
}
