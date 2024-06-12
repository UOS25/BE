package uos.uos25.user.dto.response;

import lombok.Data;
import uos.uos25.user.entity.Customer;

@Data
public class CustomerMileageGetResponseDTO {
    private final String phoneNumber;
    private final Integer mileage;

    public static CustomerMileageGetResponseDTO fromEntity(Customer customer) {
        return new CustomerMileageGetResponseDTO(customer.getPhoneNumber(), customer.getMileage());
    }
}
