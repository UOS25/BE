package uos.uos25.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmployeePosition {
    EMPLOYEE("직원"),
    MANAGER("매니저"),
    BOSS("사장"),
    HEADQUARTER_STAFF("본사");

    private String position;
}
