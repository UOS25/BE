package uos.uos25.customer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.customer.dto.request.CustomerRequestDTO;
import uos.uos25.customer.dto.response.CustomerResponseDTO;
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
    public void createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = Customer.builder()
                .customerHP(customerRequestDTO.getCustomerHP())
                .nickname(customerRequestDTO.getNickname())
                .build();

        customerRepository.save(customer);
    }

    // read
    // 조회 가능한 모든 고객 정보를 불러옵니다.
    public List<CustomerResponseDTO> findAllCustomers() {
        List<Customer> allCustomers =  customerRepository.findAll();

        return allCustomers.stream().map(CustomerResponseDTO::fromEntity).toList();
    }

    // read
    // 고객 전화번호로 해당 고객의 정보를 불러옵니다.
    public Customer findCustomerByHP(String customerHP) {
        return customerRepository.findByCustomerHP(customerHP)
                .orElseThrow(() -> new CustomerNotFoundException("해당 전화번호를 가진 고객이 존재하지 않습니다."));
    }

    // update
    // 고객 정보를 업데이트합니다.
    public void updateCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer findCustomer = customerRepository.findByCustomerHP(customerRequestDTO.getCustomerHP())
                .orElseThrow(() -> new CustomerNotFoundException("해당 아이디의 고객이 존재하지 않습니다."));
        // 정보 수정
        findCustomer.changeCustomerInfo(customerRequestDTO.getCustomerHP(), customerRequestDTO.getNickname());
    }

    // update
    // 마일리지를 적립합니다. 적립되는 마일리지 양은 이벤트 등을 고려하여, 판매 과정에서 계산됩니다.
    public void earnMileage(Integer mileage, String customerHP) {
        Customer findCustomer = customerRepository.findByCustomerHP(customerHP)
                .orElseThrow(() -> new CustomerNotFoundException("해당 전화번호를 가진 고객이 존재하지 않습니다."));

        // 마일리지를 적립합니다.
        findCustomer.earnMileage(mileage);
    }

    // delete
    // 고객 정보를 삭제합니다.
    public void deleteCustomer(String customerHP) {
        customerRepository.deleteByCustomerHP(customerHP);
    }
}
