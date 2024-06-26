package uos.uos25.user.dto.request;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class CustomerCreateRequestDTO {

    @NotBlank(message = "전화번호를 입력하세요.")
    @Schema(example = "010-4321-4321")
    private final String phoneNumber;

    @NotBlank(message = "이름을 입력하세요.")
    @Schema(example = "유현승")
    private final String nickname;
}
