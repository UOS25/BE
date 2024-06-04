package uos.uos25.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.customer.dto.request.CustomerRequestDTO;
import uos.uos25.customer.dto.request.CustomerMileageUpdateRequestDTO;
import uos.uos25.customer.dto.response.CustomerResponseDTO;
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

    // readByCustomerHP
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<CustomerResponseDTO> findCustomerById(@PathVariable String phoneNumber) {
        Customer customer = customerService.findById(phoneNumber);

        return ResponseEntity.ok(CustomerResponseDTO.fromEntity(customer));
    }

    // update
    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        customerService.updateCustomer(customerRequestDTO);

        String msg = "고객 수정이 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // 마일리지 적립
    @PatchMapping("/mileage")
    public ResponseEntity<?> earnMileage(@RequestBody CustomerMileageUpdateRequestDTO customerMileageUpdateRequestDTO) {
        customerService.earnMileage(customerMileageUpdateRequestDTO.getMileage(), customerMileageUpdateRequestDTO.getPhoneNumber());

        String msg = "마일리지 적립이 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String phoneNumber) {
        customerService.deleteCustomer(phoneNumber);

        String msg = "고객 삭제가 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
