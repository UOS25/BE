package uos.uos25.receipt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.Employee.entity.Employee;
import uos.uos25.Employee.service.EmployeeService;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.service.CustomerService;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.exception.ReceiptNotFound;
import uos.uos25.receipt.repository.ReceiptRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    public Receipt create(Long employeeId, String customerHP, Integer age, String gender){
        Employee employee = employeeService.findById(employeeId);
        Customer customer = customerService.findCustomerByHP(customerHP);

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
}
