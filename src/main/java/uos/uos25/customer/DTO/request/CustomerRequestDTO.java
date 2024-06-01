package uos.uos25.customer.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class CustomerRequestDTO {

    @NotBlank(message = "전화번호를 입력하세요.")
    private final String customerHP;
    @NotBlank(message = "이름을 입력하세요.")
    private final String nickname;

}
