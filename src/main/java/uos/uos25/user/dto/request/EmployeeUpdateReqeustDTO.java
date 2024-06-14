package uos.uos25.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class EmployeeUpdateReqeustDTO {
    @Schema(example = "1")
    private Long employeeId;

    @Schema(example = "김민서")
    private String employeeName = null;

    @Schema(example = "점장")
    private String position = null;

    @Schema(example = "990222-7654321")
    private String registrationNumber = null;

    @Schema(example = "10000")
    private Integer salary = null;

    @Schema(example = "NIGHT")
    private String partTime = null;

    @Schema(example = "110-485-122345")
    private String account = null;

    @Schema(example = "신한은행")
    private String bank = null;

    @Schema(example = "1")
    private Long shopId = null;

    @Builder
    public EmployeeUpdateReqeustDTO(
            Long employeeId,
            String employeeName,
            String position,
            String registrationNumber,
            Integer salary,
            String partTime,
            String account,
            String bank,
            Long shopId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.registrationNumber = registrationNumber;
        this.salary = salary;
        this.partTime = partTime;
        this.account = account;
        this.bank = bank;
        this.shopId = shopId;
    }
}
