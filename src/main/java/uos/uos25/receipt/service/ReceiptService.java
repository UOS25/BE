package uos.uos25.receipt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.service.CustomerService;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.service.EmployeeService;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.exception.ReceiptNotFound;
import uos.uos25.receipt.repository.ReceiptRepository;

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
                        .purchaseStatus("구매완료")
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
