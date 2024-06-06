package uos.uos25.employee.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import uos.uos25.employee.entity.EmployeeWorkingHistory;

@Data
public class EmployeeWorkingHistoryGetResponseDTO {
    private final Long employeeWorkingHistoryId;
    private final Long employeeId;
    private final String employeeName;
    private final Long workingHour;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    @Builder
    public EmployeeWorkingHistoryGetResponseDTO(
            Long employeeWorkingHistoryId,
            Long employeeId,
            String employeeName,
            Long workingHour,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        this.employeeWorkingHistoryId = employeeWorkingHistoryId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.workingHour = workingHour;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static EmployeeWorkingHistoryGetResponseDTO fromEntity(
            EmployeeWorkingHistory employeeWorkingHistory) {
        return EmployeeWorkingHistoryGetResponseDTO.builder()
                .employeeWorkingHistoryId(employeeWorkingHistory.getEmployeeWorkingHistoryId())
                .employeeId(employeeWorkingHistory.getEmployee().getEmployeeId())
                .employeeName(employeeWorkingHistory.getEmployee().getEmployeeName())
                .workingHour(employeeWorkingHistory.getWorkingHour())
                .startDateTime(employeeWorkingHistory.getStartDateTime())
                .endDateTime(employeeWorkingHistory.getEndDateTime())
                .build();
    }
}
