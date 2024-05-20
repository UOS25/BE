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
    @Id
    @Column(length = 20)
    private Long receiptId;

    private LocalDateTime purchaseDate;
    @Column(length = 18)
    private String purchaseStatus;
    private Integer age;
    @Column(length = 18)
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "receipt")
    private List<ReceiptDetail> receiptDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
