package uos.uos25.user.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import uos.uos25.user.entity.Customer;

@Data
@AllArgsConstructor
public class CustomerCreateResponseDTO {
    private final String phoneNumber;
    private final String nickname;
    private final LocalDateTime joinDate;
    private final Integer mileage;

    public static CustomerCreateResponseDTO fromEntity(Customer customer) {
        return new CustomerCreateResponseDTO(
                customer.getPhoneNumber(),
                customer.getNickname(),
                customer.getJoinDate(),
                customer.getMileage());
    }
}
