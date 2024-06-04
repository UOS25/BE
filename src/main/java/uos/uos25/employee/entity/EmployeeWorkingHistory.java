package uos.uos25.employee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorkingHistory {

    @Id @GeneratedValue
    @Column(nullable = false)
    private Long employeeWorkingHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    private Long workingHour;

    @Builder
    public EmployeeWorkingHistory(Employee employee, LocalDateTime startDateTime, LocalDateTime endDateTime, Long workingHour) {
        this.employee = employee;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.workingHour = workingHour;
    }
}