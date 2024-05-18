package uos.uos25.Employee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uos.uos25.entity.Receipt;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "employee")
    private List<Receipt> receipts = new ArrayList<>();
}
