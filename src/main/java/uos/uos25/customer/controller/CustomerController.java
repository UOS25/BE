package uos.uos25.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.customer.DTO.request.CustomerRequestDTO;
import uos.uos25.customer.DTO.response.CustomerResponseDTO;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.service.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    // create
    @PostMapping("/join")
    public ResponseEntity<?> joinCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        customerService.createCustomer(customerRequestDTO);

        String msg = "고객 등록이 완료되었습니다.";

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // readAll
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    // readById
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> findCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);

        return ResponseEntity.ok(CustomerResponseDTO.fromEntity(customer));
    }

    // update
    @PutMapping("/update/{customerId}")
    public ResponseEntity<?> updateCustomerById(@PathVariable Long customerId, @RequestBody CustomerRequestDTO customerRequestDTO) {
        customerService.updateCustomer(customerRequestDTO, customerId);

        String msg = "고객 수정이 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 마일리지 적립
    @PostMapping("/mileage/{customerId}")
    public ResponseEntity<?> earnMileage(@PathVariable Long customerId, @RequestBody Integer mileage) {
        customerService.earnMileage(mileage, customerId);

        String msg = "마일리지 적립이 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);

        String msg = "고객 삭제가 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
