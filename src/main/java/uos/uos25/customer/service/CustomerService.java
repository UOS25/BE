package uos.uos25.customer.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.customer.dto.request.CustomerCreateRequestDTO;
import uos.uos25.customer.dto.request.CustomerUpdateRequestDTO;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.exception.CustomerAlreadyExists;
import uos.uos25.customer.exception.CustomerNotFoundException;
import uos.uos25.customer.repository.CustomerRepository;
import uos.uos25.receipt.entity.Receipt;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        validateAlreadyExists(customerCreateRequestDTO.getPhoneNumber());

        Customer customer =
                Customer.builder()
                        .phoneNumber(customerCreateRequestDTO.getPhoneNumber())
                        .nickname(customerCreateRequestDTO.getNickname())
                        .build();

        return customerRepository.save(customer);
    }

    private void validateAlreadyExists(String phoneNumber) {
        customerRepository
                .findById(phoneNumber)
                .ifPresent(
                        customer -> {
                            throw new CustomerAlreadyExists();
                        });
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findById(String phoneNumber) {
        return customerRepository
                .findById(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException());
    }

    public Customer updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer findCustomer =
                customerRepository
                        .findById(customerUpdateRequestDTO.getPhoneNumber())
                        .orElseThrow(() -> new CustomerNotFoundException());
        // 정보 수정
        findCustomer.changeCustomerInfo(customerUpdateRequestDTO.getNickname());

        return findCustomer;
    }

    public void earnMileage(Integer mileage, String phoneNumber) {
        Customer findCustomer =
                customerRepository
                        .findById(phoneNumber)
                        .orElseThrow(() -> new CustomerNotFoundException());

        // 마일리지를 적립합니다.
        findCustomer.earnMileage(mileage);
    }

    /**
     * 영수증에 해당 고객 null로 변경 후 유저 삭제
     *
     * @param phoneNumber 고객 PK
     */
    public void deleteCustomer(String phoneNumber) {
        // 영수증 안에 고객 null로 변경
        List<Receipt> receipts = findById(phoneNumber).getReceipts();
        receipts.stream().forEach(receipt -> receipt.removeCustomer());

        // 고객 삭제
        customerRepository.deleteById(phoneNumber);
    }
}
