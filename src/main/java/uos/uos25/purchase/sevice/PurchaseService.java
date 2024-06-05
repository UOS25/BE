package uos.uos25.purchase.sevice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.service.CustomerService;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.service.EmployeeService;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.inventory.service.InventoryService;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.purchase.dto.ItemInfo;
import uos.uos25.purchase.dto.TotalPrice;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.entity.ReceiptDetail;
import uos.uos25.receipt.service.ReceiptDetailService;
import uos.uos25.receipt.service.ReceiptService;
import uos.uos25.shop.entity.Shop;

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
        Customer customer = customerService.findById(phoneNumber);
        Employee employee = employeeService.findById(employeeId);
        Shop shop = employee.getShop();

        TotalPrice totalPrice = new TotalPrice();
        for (ItemInfo itemInfo : itemInfos) {
            Product product = productService.findById(itemInfo.getBarcode());
            Integer ea = itemInfo.getEa();

            // 재고 수량 차감
            Inventory inventory =
                    inventoryService.findInventoryByShopIdAndProductId(
                            shop.getShopId(), product.getBarcode());
            inventory.minusEa(ea);

            totalPrice.plus(product.getCustomerPrice() * ea);

            receiptDetailService.create(receipt, product.getBarcode(), itemInfo.getEa());
        }

        totalPrice.discountMileage(mileage);
        customer.earnMileage(totalPrice.get());
    }

    @Transactional
    public void cancel(Long receiptId) {
        // 영수증 status 변경
        Receipt receipt = receiptService.findById(receiptId);
        receipt.cancelReceipt();

        // 재고에 환불한 상품 개수 업데이트
        Shop shop = receipt.getEmployee().getShop();
        List<ReceiptDetail> receiptDetails = receipt.getReceiptDetails();
        for (ReceiptDetail receiptDetail : receiptDetails) {
            Product product = receiptDetail.getProduct();
            Integer ea = receiptDetail.getEa();

            Inventory inventory =
                    inventoryService.findInventoryByShopIdAndProductId(
                            shop.getShopId(), product.getBarcode());
            inventory.plusEa(ea);
        }
    }
}
