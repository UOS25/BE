package uos.uos25.Employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uos.uos25.common.BaseEntity;
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
public class Employee extends BaseEntity {

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

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    // Cascade 전용 필드.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receipt> receipts = new ArrayList<>();

    // Cascade 전용 필드.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeWorkingHistory> workingHistories = new ArrayList<>();

}
