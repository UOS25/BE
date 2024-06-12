package uos.uos25.user.receipt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.user.entity.Customer;
import uos.uos25.user.entity.Employee;
import uos.uos25.user.receipt.entity.Receipt;
import uos.uos25.user.receipt.entity.ReceiptStatus;
import uos.uos25.user.receipt.exception.ReceiptNotFound;
import uos.uos25.user.receipt.repository.ReceiptRepository;
import uos.uos25.user.service.CustomerService;
import uos.uos25.user.service.EmployeeService;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    public Receipt create(Long employeeId, String phoneNumber, Integer age, String gender) {
        Employee employee = employeeService.findById(employeeId);
        Customer customer = null;
        if (phoneNumber != null) customer = customerService.findById(phoneNumber);

        Receipt receipt =
                Receipt.builder()
                        .employee(employee)
                        .customer(customer)
                        .age(age)
                        .gender(gender)
                        .purchaseStatus(ReceiptStatus.COMPLETED.getStatus())
                        .build();
        return receiptRepository.save(receipt);
    }

    public Receipt findById(Long receiptId) {
        return receiptRepository.findById(receiptId).orElseThrow(() -> new ReceiptNotFound());
    }

    public List<Receipt> findByCustomerPhoneNumber(String phoneNumber) {
        return receiptRepository.findAllByCustomerPhoneNumber(phoneNumber);
    }
}
