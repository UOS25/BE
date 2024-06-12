package uos.uos25.user.dto.response;

import lombok.Builder;
import lombok.Data;
import uos.uos25.user.entity.Employee;

@Data
public class EmployeeCreateResponseDTO {
    private Long employeeId;
    private String employeeName;
    private String position;
    private String registrationNumber;
    private Integer salary;
    private String partTime;
    private String account;
    private String bank;
    private Long shopId;

    @Builder
    public EmployeeCreateResponseDTO(
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

    public static EmployeeCreateResponseDTO fromEntity(Employee employee) {
        return EmployeeCreateResponseDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .position(employee.getPosition())
                .registrationNumber(employee.getRegistrationNumber())
                .salary(employee.getSalary())
                .partTime(employee.getPartTime().name())
                .account(employee.getAccount())
                .bank(employee.getBank())
                .shopId(employee.getShop().getShopId())
                .build();
    }
}
