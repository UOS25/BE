package uos.uos25.customer.dto.response;


import lombok.Data;
import uos.uos25.customer.entity.Customer;

import java.time.LocalDateTime;

@Data
public class CustomerMileageGetResponseDTO {
    private final String phoneNumber;
    private final Integer mileage;

    public static CustomerMileageGetResponseDTO fromEntity(Customer customer) {
        return new CustomerMileageGetResponseDTO(
                customer.getPhoneNumber(),
                customer.getMileage());
    }
}
