package uos.uos25.user.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.user.dto.request.CustomerCreateRequestDTO;
import uos.uos25.user.dto.request.CustomerUpdateRequestDTO;
import uos.uos25.user.dto.response.CustomerCreateResponseDTO;
import uos.uos25.user.dto.response.CustomerGetResponseDTO;
import uos.uos25.user.dto.response.CustomerMileageGetResponseDTO;
import uos.uos25.user.dto.response.CustomerUpdateResponseDTO;
import uos.uos25.user.entity.Customer;
import uos.uos25.user.service.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@Tag(name = "고객")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "고객 등록")
    @PostMapping
    public ResponseEntity<CustomerCreateResponseDTO> joinCustomer(
            @Valid @RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        Customer customer = customerService.createCustomer(customerCreateRequestDTO);
        CustomerCreateResponseDTO customerCreateResponseDTO =
                CustomerCreateResponseDTO.fromEntity(customer);

        return ResponseEntity.ok(customerCreateResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CustomerGetResponseDTO>> findAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        List<CustomerGetResponseDTO> customerGetResponseDTOS =
                customers.stream()
                        .map(customer -> CustomerGetResponseDTO.fromEntity(customer))
                        .toList();

        return ResponseEntity.ok(customerGetResponseDTOS);
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<CustomerGetResponseDTO> findCustomerById(
            @PathVariable String phoneNumber) {
        Customer customer = customerService.findById(phoneNumber);

        return ResponseEntity.ok(CustomerGetResponseDTO.fromEntity(customer));
    }

    @Operation(summary = "고객 정보 수정")
    @PatchMapping
    public ResponseEntity<CustomerUpdateResponseDTO> updateCustomer(
            @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer customer = customerService.updateCustomer(customerUpdateRequestDTO);
        CustomerUpdateResponseDTO customerUpdateResponseDTO =
                CustomerUpdateResponseDTO.fromEntity(customer);

        return ResponseEntity.ok(customerUpdateResponseDTO);
    }

    @GetMapping("/mileage/{phoneNumber}")
    public ResponseEntity<CustomerMileageGetResponseDTO> getMileage(
            @PathVariable String phoneNumber) {
        Customer customer = customerService.findById(phoneNumber);
        CustomerMileageGetResponseDTO customerMileageGetResponseDTO =
                CustomerMileageGetResponseDTO.fromEntity(customer);

        return ResponseEntity.ok(customerMileageGetResponseDTO);
    }

    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(example = "01012341234") @PathVariable String phoneNumber) {
        customerService.deleteCustomer(phoneNumber);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
