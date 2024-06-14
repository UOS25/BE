package uos.uos25.user.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeCreateReqeustDTO {
    @NotBlank(message = "직원 이름을 입력하세요.")
    @Schema(example = "유현승")
    private String employeeName;

    @NotBlank(message = "직급을 입력하세요.")
    @Schema(example = "점원")
    private String position;

    @NotBlank(message = "주민번호를 입력하세요.")
    @Schema(example = "990222-1234567")
    private String registrationNumber;

    @Min(value = -1, message = "0 이상의 값을 입력하세요.")
    @Schema(example = "12000")
    private Integer salary;

    @NotBlank(message = "업무 시간을 입력하세요.")
    @Schema(example = "DAY")
    private String partTime;

    @NotBlank(message = "계좌번호를 입력하세요.")
    @Schema(example = "110-485-577342")
    private String account;

    @NotBlank(message = "은행명을 입력하세요.")
    @Schema(example = "농협은행")
    private String bank;

    @Schema(example = "1")
    private Long shopId;
}
