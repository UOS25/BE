package uos.uos25.Employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorkingHistory {

    @Id @GeneratedValue
    private Long employeeWorkingHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Long workingHour;

}
