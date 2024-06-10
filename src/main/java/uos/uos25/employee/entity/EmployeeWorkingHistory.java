package uos.uos25.employee.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;
import uos.uos25.common.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorkingHistory extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long employeeWorkingHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime startDateTime;

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    private Long workingHour;

    @Builder
    public EmployeeWorkingHistory(
            Employee employee,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Long workingHour) {
        this.employee = employee;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.workingHour = workingHour;
    }
}
