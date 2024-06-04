package uos.uos25.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id @GeneratedValue
    @Column(nullable = false)
    private Long employeeId; // Long으로 변경

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false)
    private LocalDateTime employmentDate;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private Integer salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartTime partTime;

    @Column(nullable = false)
    private String account;

    @Column(nullable = true)
    private LocalDateTime firedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    // Cascade 전용 필드.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Receipt> receipts = new ArrayList<>();

    // Cascade 전용 필드.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EmployeeWorkingHistory> workingHistories = new ArrayList<>();

    @Builder

    public Employee(String employeeName, LocalDateTime employmentDate, String position, String registrationNumber, Integer salary, PartTime partTime, String account, Shop shop) {
        this.employeeName = employeeName;
        this.employmentDate = employmentDate;
        this.position = position;
        this.registrationNumber = registrationNumber;
        this.salary = salary;
        this.partTime = partTime;
        this.account = account;
        this.shop = shop;
    }
}
