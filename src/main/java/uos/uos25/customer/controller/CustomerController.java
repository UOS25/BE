package uos.uos25.customer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.customer.dto.request.CustomerCreateRequestDTO;
import uos.uos25.customer.dto.request.CustomerUpdateRequestDTO;
import uos.uos25.customer.dto.response.CustomerCreateResponseDTO;
import uos.uos25.customer.dto.response.CustomerGetResponseDTO;
import uos.uos25.customer.dto.response.CustomerMileageGetResponseDTO;
import uos.uos25.customer.dto.response.CustomerUpdateResponseDTO;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.service.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@Tag(name = "고객")
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
    public ResponseEntity<CustomerGetResponseDTO> findCustomerById(@PathVariable String phoneNumber) {
        Customer customer = customerService.findById(phoneNumber);

        return ResponseEntity.ok(CustomerGetResponseDTO.fromEntity(customer));
    }

    @PutMapping
    public ResponseEntity<CustomerUpdateResponseDTO> updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer customer = customerService.updateCustomer(customerUpdateRequestDTO);
        CustomerUpdateResponseDTO customerUpdateResponseDTO = CustomerUpdateResponseDTO.fromEntity(customer);

        return ResponseEntity.ok(customerUpdateResponseDTO);
    }

    @GetMapping("/mileage/{phoneNumber}")
    public ResponseEntity<CustomerMileageGetResponseDTO> getMileage(@PathVariable String phoneNumber) {
        Customer customer = customerService.findById(phoneNumber);
        CustomerMileageGetResponseDTO customerMileageGetResponseDTO = CustomerMileageGetResponseDTO.fromEntity(customer);

        return ResponseEntity.ok(customerMileageGetResponseDTO);
    }

    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable String phoneNumber) {
        customerService.deleteCustomer(phoneNumber);

        return ResponseEntity.ok(true);
    }
}
