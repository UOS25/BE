package uos.uos25.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeInfo {
    private final Long employeeId;
    private String employeeName;
}
