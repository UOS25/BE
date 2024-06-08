package uos.uos25.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeInfo {
    private final Long employeeId;
    private String employeeName;
}
