package uos.uos25.customer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.customer.dto.request.CustomerCreateRequestDTO;
import uos.uos25.customer.dto.response.CustomerCreateResponseDTO;
import uos.uos25.customer.dto.response.CustomerGetResponseDTO;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.exception.CustomerNotFoundException;
import uos.uos25.customer.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    // create
    // 고객 정보를 생성합니다.
    public Customer createCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer = Customer.builder()
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
        return customerRepository.findById(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException("해당 전화번호를 가진 고객이 존재하지 않습니다."));
    }

    // update
    // 고객 정보를 업데이트합니다.
    public void updateCustomer(CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer findCustomer = customerRepository.findById(customerCreateRequestDTO.getPhoneNumber())
                .orElseThrow(() -> new CustomerNotFoundException("해당 아이디의 고객이 존재하지 않습니다."));
        // 정보 수정
        findCustomer.changeCustomerInfo(customerCreateRequestDTO.getPhoneNumber(), customerCreateRequestDTO.getNickname());
    }

    // update
    // 마일리지를 적립합니다. 적립되는 마일리지 양은 이벤트 등을 고려하여, 판매 과정에서 계산됩니다.
    public void earnMileage(Integer mileage, String phoneNumber) {
        Customer findCustomer = customerRepository.findById(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException("해당 전화번호를 가진 고객이 존재하지 않습니다."));

        // 마일리지를 적립합니다.
        findCustomer.earnMileage(mileage);
    }

    // delete
    // 고객 정보를 삭제합니다.
    public void deleteCustomer(String phoneNumber) {
        customerRepository.deleteById(phoneNumber);
    }
}
