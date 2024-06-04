package uos.uos25.receipt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.service.EmployeeService;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.service.CustomerService;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.entity.ReceiptDetail;
import uos.uos25.receipt.exception.ReceiptNotFound;
import uos.uos25.receipt.repository.ReceiptRepository;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final ReceiptDetailService receiptDetailService;

    public Receipt create(Long employeeId, String phoneNumber, Integer age, String gender){
        Employee employee = employeeService.findById(employeeId);
        Customer customer = customerService.findById(phoneNumber);

        Receipt receipt = Receipt.builder()
                .employee(employee)
                .customer(customer)
                .age(age)
                .gender(gender)
                .purchaseStatus("결제 예정")
                .purchaseDate(LocalDateTime.now())
                .build();
        return receiptRepository.save(receipt);
    }

    public Receipt findById(Long receiptId){
        return receiptRepository.findById(receiptId).orElseThrow(() -> new ReceiptNotFound());
    }

    public ReceiptGetResponseDTO entityToDTO(Receipt receipt){
        Employee employee = receipt.getEmployee();
        Shop shop = employee.getShop();
        List<ReceiptDetail> receiptDetails = receipt.getReceiptDetails();
        List<ItemInfo> itemInfos = receiptDetails.stream().map(receiptDetail -> ItemInfo.fromReceiptDetail(receiptDetail)).toList();

        return ReceiptGetResponseDTO.builder()
                .receiptId(receipt.getReceiptId())
                .shopName(shop.getShopName())
                .itemInfos(itemInfos)
                .purchaseDate(receipt.getPurchaseDate())
                .build();
    }
}
