package uos.uos25.customer.dto.request;

import lombok.Data;

@Data
public class CustomerMileageUpdateRequestDTO {
    private final String phoneNumber;
    private final Integer mileage;
}
