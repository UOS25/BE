package uos.uos25.customer.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import uos.uos25.customer.entity.Customer;

import java.time.LocalDateTime;

@Data
public class CustomerGetResponseDTO {
    private final String phoneNumber;
    private final String nickname;
    private final LocalDateTime joinDate;
    private final Integer mileage;

    public static CustomerGetResponseDTO fromEntity(Customer customer) {
        return new CustomerGetResponseDTO(
                customer.getPhoneNumber(),
                customer.getNickname(),
                customer.getJoinDate(),
                customer.getMileage());
    }
}
