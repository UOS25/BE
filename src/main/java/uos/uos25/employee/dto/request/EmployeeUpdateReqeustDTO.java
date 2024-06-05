package uos.uos25.employee.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeUpdateReqeustDTO {
    @NotBlank(message = "직원 id를 입력하세요.")
    private Long employeeId;

    @NotBlank(message = "직원 이름을 입력하세요.")
    private String employeeName;

    @NotBlank(message = "직급을 입력하세요.")
    private String position;

    @NotBlank(message = "주민번호를 입력하세요.")
    private String registrationNumber;

    @Min(value = -1, message = "0 이상의 값을 입력하세요.")
    private Integer salary;

    @NotBlank(message = "업무 시간을 입력하세요.")
    private String partTime;

    @NotBlank(message = "계좌번호를 입력하세요.")
    private String account;

    @NotBlank(message = "지점이름을 입력하세요.")
    private Long shopId;
}
