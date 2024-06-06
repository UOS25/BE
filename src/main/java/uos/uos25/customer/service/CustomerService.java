package uos.uos25.customer.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.customer.dto.request.CustomerCreateRequestDTO;
import uos.uos25.customer.dto.request.CustomerUpdateRequestDTO;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.exception.CustomerNotFoundException;
import uos.uos25.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    // create
    // 고객 정보를 생성합니다.
    public Customer createCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer =
                Customer.builder()
                        .phoneNumber(customerCreateRequestDTO.getPhoneNumber())
                        .nickname(customerCreateRequestDTO.getNickname())
                        .build();

        return customerRepository.save(customer);
    }

    // read
    // 조회 가능한 모든 고객 정보를 불러옵니다.
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    // read
    // 고객 전화번호로 해당 고객의 정보를 불러옵니다.
    public Customer findById(String phoneNumber) {
        return customerRepository
                .findById(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException());
    }

    // update
    // 고객 정보를 업데이트합니다.
    public Customer updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer findCustomer =
                customerRepository
                        .findById(customerUpdateRequestDTO.getPhoneNumber())
                        .orElseThrow(() -> new CustomerNotFoundException());
        // 정보 수정
        findCustomer.changeCustomerInfo(customerUpdateRequestDTO.getNickname());

        return findCustomer;
    }

    // update
    // 마일리지를 적립합니다. 적립되는 마일리지 양은 이벤트 등을 고려하여, 판매 과정에서 계산됩니다.
    public void earnMileage(Integer mileage, String phoneNumber) {
        Customer findCustomer =
                customerRepository
                        .findById(phoneNumber)
                        .orElseThrow(() -> new CustomerNotFoundException());

        // 마일리지를 적립합니다.
        findCustomer.earnMileage(mileage);
    }

    // delete
    // 고객 정보를 삭제합니다.
    public void deleteCustomer(String phoneNumber) {
        customerRepository.deleteById(phoneNumber);
    }
}
