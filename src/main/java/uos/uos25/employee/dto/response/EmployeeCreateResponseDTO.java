package uos.uos25.employee.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;
import uos.uos25.employee.entity.Employee;

@Data
public class EmployeeCreateResponseDTO {

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

    @Builder
    public EmployeeCreateResponseDTO(
            String employeeName,
            String position,
            String registrationNumber,
            Integer salary,
            String partTime,
            String account,
            Long shopId) {
        this.employeeName = employeeName;
        this.position = position;
        this.registrationNumber = registrationNumber;
        this.salary = salary;
        this.partTime = partTime;
        this.account = account;
        this.shopId = shopId;
    }

    public static EmployeeCreateResponseDTO fromEntity(Employee employee) {
        return EmployeeCreateResponseDTO.builder()
                .employeeName(employee.getEmployeeName())
                .position(employee.getPosition())
                .registrationNumber(employee.getRegistrationNumber())
                .salary(employee.getSalary())
                .partTime(employee.getPartTime().name())
                .account(employee.getAccount())
                .shopId(employee.getShop().getShopId())
                .build();
    }
}
