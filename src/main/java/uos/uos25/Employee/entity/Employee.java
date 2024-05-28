package uos.uos25.Employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id @GeneratedValue
    @Column(length = 30)
    private Long employeeId; // Long으로 변경

    @Column(length = 20)
    private String employeeName;
    private LocalDateTime employmentDate;
    @Column(length = 18)
    private String position;
    @Column(length = 30)
    private String registrationNumber;
    private Long salary;

    @Enumerated(EnumType.STRING)
    private PartTime partTime;

    @Column(length = 18)
    private String account;
    private LocalDateTime firedDate;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    // Cascade 전용 필드.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receipt> receipts = new ArrayList<>();
    // Cascade 전용 필드.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeWorkingHistory> workingHistories = new ArrayList<>();

}
