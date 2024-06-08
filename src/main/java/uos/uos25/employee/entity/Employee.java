package uos.uos25.employee.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.shop.entity.Shop;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long employeeId;

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

    @Column(nullable = false)
    private String bank;

    @Column(nullable = true)
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
