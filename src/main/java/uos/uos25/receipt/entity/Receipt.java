package uos.uos25.receipt.entity;

import jakarta.persistence.*;
import uos.uos25.Employee.entity.Employee;
import uos.uos25.customer.entity.Customer;
import uos.uos25.entity.ReceiptDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Receipt {
    @Id @GeneratedValue
    @Column(nullable = false)
    private Long receiptId;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private String purchaseStatus;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "receipt")
    private List<ReceiptDetail> receiptDetails = new ArrayList<>();

}
