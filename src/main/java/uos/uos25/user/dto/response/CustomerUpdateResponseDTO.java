package uos.uos25.user.dto.response;

import java.time.LocalDateTime;

import lombok.Data;
import uos.uos25.user.entity.Customer;

@Data
public class CustomerUpdateResponseDTO {
    private final String phoneNumber;
    private final String nickname;
    private final LocalDateTime joinDate;
    private final Integer mileage;

    public static CustomerUpdateResponseDTO fromEntity(Customer customer) {
        return new CustomerUpdateResponseDTO(
                customer.getPhoneNumber(),
                customer.getNickname(),
                customer.getJoinDate(),
                customer.getMileage());
    }
}
