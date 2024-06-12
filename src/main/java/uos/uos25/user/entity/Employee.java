package uos.uos25.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import uos.uos25.shop.entity.Shop;
import uos.uos25.user.receipt.entity.Receipt;

@Entity
@Table(indexes = {@Index(name = "I_EMPLOYEE_01", columnList = "shop_id")})
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long employeeId;

    @Column(length = 50, nullable = false)
    private String employeeName;

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime employmentDate;

    @Column(length = 50, nullable = false)
    private String position;

    @Column(length = 14, nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private Integer salary;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PartTime partTime;

    @Column(length = 50, nullable = false)
    private String account;

    @Column(length = 50, nullable = false)
    private String bank;

    @Column(nullable = true, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime firedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    // Cascade 전용 필드.
    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Receipt> receipts = new ArrayList<>();

    // Cascade 전용 필드.
    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<EmployeeWorkingHistory> workingHistories = new ArrayList<>();

    @Builder
    public Employee(
            String employeeName,
            LocalDateTime employmentDate,
            String position,
            String registrationNumber,
            Integer salary,
            PartTime partTime,
            String account,
            String bank,
            Shop shop) {
        this.employeeName = employeeName;
        this.employmentDate = employmentDate;
        this.position = position;
        this.registrationNumber = registrationNumber;
        this.salary = salary;
        this.partTime = partTime;
        this.account = account;
        this.bank = bank;
        this.shop = shop;
    }

    public void setPartTime(PartTime partTime) {
        this.partTime = partTime;
    }

    public void setFiredDate(LocalDateTime firedDate) {
        this.firedDate = firedDate;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void update(
            String employeeName,
            String position,
            String registrationNumber,
            Integer salary,
            String partTime,
            String account,
            String bank,
            Shop shop) {
        if (employeeName != null) this.employeeName = employeeName;
        if (position != null) this.position = position;
        if (registrationNumber != null) this.registrationNumber = registrationNumber;
        if (salary != null) this.salary = salary;
        if (partTime != null) this.partTime = PartTime.valueOf(partTime);
        if (account != null) this.account = account;
        if (bank != null) this.bank = bank;
        if (shop != null) this.shop = shop;
    }

    public Integer calculateSalary(Long totalWorkingHour) {
        return totalWorkingHour.intValue() * salary;
    }
}
