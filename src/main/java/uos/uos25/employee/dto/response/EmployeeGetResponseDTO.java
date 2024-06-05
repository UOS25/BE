package uos.uos25.employee.dto.response;

import lombok.Builder;
import lombok.Data;
import uos.uos25.employee.entity.Employee;

@Data
public class EmployeeGetResponseDTO {
    private Long employeeId;
    private String employeeName;
    private String position;
    private String registrationNumber;
    private Integer salary;
    private String partTime;
    private String account;
    private Long shopId;

    @Builder
    public EmployeeGetResponseDTO(
            Long employeeId,
            String employeeName,
            String position,
            String registrationNumber,
            Integer salary,
            String partTime,
            String account,
            Long shopId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.registrationNumber = registrationNumber;
        this.salary = salary;
        this.partTime = partTime;
        this.account = account;
        this.shopId = shopId;
    }

    public static EmployeeGetResponseDTO fromEntity(Employee employee) {
        return EmployeeGetResponseDTO.builder()
                .employeeId(employee.getEmployeeId())
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
