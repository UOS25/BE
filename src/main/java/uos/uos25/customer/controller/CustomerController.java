package uos.uos25.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.customer.dto.request.CustomerCreateRequestDTO;
import uos.uos25.customer.dto.request.CustomerMileageUpdateRequestDTO;
import uos.uos25.customer.dto.response.CustomerCreateResponseDTO;
import uos.uos25.customer.dto.response.CustomerGetResponseDTO;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.service.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerCreateResponseDTO> joinCustomer(@Valid @RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer = customerService.createCustomer(customerCreateRequestDTO);
        CustomerCreateResponseDTO customerCreateResponseDTO = CustomerCreateResponseDTO.fromEntity(customer);

        return ResponseEntity.ok(customerCreateResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CustomerGetResponseDTO>> findAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        List<CustomerGetResponseDTO> customerGetResponseDTOS = customers.stream().map(customer -> CustomerGetResponseDTO.fromEntity(customer)).toList();

        return ResponseEntity.ok(customerGetResponseDTOS);
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<CustomerCreateResponseDTO> findCustomerById(@PathVariable String phoneNumber) {
        Customer customer = customerService.findById(phoneNumber);

        return ResponseEntity.ok(CustomerCreateResponseDTO.fromEntity(customer));
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        customerService.updateCustomer(customerCreateRequestDTO);

        String msg = "고객 수정이 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/mileage/{phoneNumber}")
    public ResponseEntity<?> earnMileage(@RequestBody CustomerMileageUpdateRequestDTO customerMileageUpdateRequestDTO) {
        customerService.earnMileage(customerMileageUpdateRequestDTO.getMileage(), customerMileageUpdateRequestDTO.getPhoneNumber());

        String msg = "마일리지 적립이 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String phoneNumber) {
        customerService.deleteCustomer(phoneNumber);

        String msg = "고객 삭제가 완료되었습니다.";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
