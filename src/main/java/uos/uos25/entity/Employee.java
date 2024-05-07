package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Employee {
    @Id
    @Column(length = 30)
    private String employeeId;

    @Column(length = 20)
    private String employeeName;
    private LocalDateTime employmentDate;
    @Column(length = 18)
    private String position;
    @Column(length = 30)
    private String registrationNumber;
    private Long salary;
    @Column(length = 4)
    private String parttime;
    @Column(length = 18)
    private String account;
    private LocalDateTime firedDate;
}
