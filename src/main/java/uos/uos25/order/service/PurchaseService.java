package uos.uos25.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.uos25.headquarter.entity.Product;
import uos.uos25.headquarter.service.ProductService;
import uos.uos25.order.dto.ItemInfo;
import uos.uos25.order.dto.TotalPrice;
import uos.uos25.shop.entity.Inventory;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.InventoryService;
import uos.uos25.user.entity.Customer;
import uos.uos25.user.entity.Employee;
import uos.uos25.user.receipt.entity.Receipt;
import uos.uos25.user.receipt.entity.ReceiptDetail;
import uos.uos25.user.receipt.service.ReceiptDetailService;
import uos.uos25.user.receipt.service.ReceiptService;
import uos.uos25.user.service.CustomerService;
import uos.uos25.user.service.EmployeeService;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final ProductService productService;
    private final ReceiptDetailService receiptDetailService;
    private final ReceiptService receiptService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final InventoryService inventoryService;

    @Transactional
    public void purchase(
            Long employeeId,
            String phoneNumber,
            Integer age,
            String gender,
            Integer mileage,
            List<ItemInfo> itemInfos) {
        Receipt receipt = receiptService.create(employeeId, phoneNumber, age, gender);
        Employee employee = employeeService.findById(employeeId);
        Shop shop = employee.getShop();

        TotalPrice totalPrice = new TotalPrice();
        for (ItemInfo itemInfo : itemInfos) {
            Product product = productService.findById(itemInfo.getBarcode());

            Integer ea = itemInfo.getEa();
            // 재고 수량 차감
            Inventory inventory =
                    inventoryService.findInventoryByShopIdAndBarcode(
                            shop.getShopId(), product.getBarcode());
            inventory.sold(ea);

            totalPrice.plus(product.getPrice() * ea);

            receiptDetailService.create(receipt, product.getBarcode(), itemInfo.getEa());
        }

        totalPrice.discountMileage(mileage);

        if (phoneNumber != null) {
            Customer customer = customerService.findById(phoneNumber);
            customer.earnMileage(totalPrice.get());
        }
    }

    @Transactional
    public Integer cancel(Long receiptId) {
        // 영수증 status 변경
        Receipt receipt = receiptService.findById(receiptId);
        receipt.validateCanBeCanceled();
        receipt.cancelReceipt();

        // 환불할 금액
        Integer canceledPrice = 0;

        // 재고에 환불한 상품 개수 업데이트
        Shop shop = receipt.getEmployee().getShop();
        List<ReceiptDetail> receiptDetails = receipt.getReceiptDetails();
        for (ReceiptDetail receiptDetail : receiptDetails) {
            Product product = receiptDetail.getProduct();
            Integer ea = receiptDetail.getEa();
            canceledPrice += product.getCustomerPrice() * ea;

            Inventory inventory =
                    inventoryService.findInventoryByShopIdAndBarcode(
                            shop.getShopId(), product.getBarcode());
            inventory.addEa(ea);
        }

        return canceledPrice;
    }
}
