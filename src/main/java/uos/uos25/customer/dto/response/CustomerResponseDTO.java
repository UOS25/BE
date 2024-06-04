package uos.uos25.customer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import uos.uos25.customer.entity.Customer;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomerResponseDTO {
    private final String customerHP;
    private final String nickname;
    private final LocalDateTime joinDate;
    private final Integer mileage;

    public static CustomerResponseDTO fromEntity(Customer customer) {
        return new CustomerResponseDTO(
                customer.getCustomerHP(),
                customer.getNickname(),
                customer.getJoinDate(),
                customer.getMileage());
    }
}
